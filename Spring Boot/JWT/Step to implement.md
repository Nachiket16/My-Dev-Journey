# 1 Add the dependency

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>


# 2 Create Class JwtAuthenticationEntryPoint implement AuthenticationEntryPoint

> Method of this class is called whenever an exception is thrown due to unauthenticated user trying to access the resource that require authentication

``` Java
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint    
//AuthEntry will be call when un authenticated exception is called
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! "+ authException.getMessage());
    }
}

```

# 3 Create JWTHelper class
> This class contains related to perform operations with jwt like generateJwtToken(), validateJwtToken(), etc...

``` Java

@Component
public class JwtHelper {

    //requirement :
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;


    @Value("${jwt.secret}")
    private String secret;


    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
```
# 4 Create JwtAuthenticationFilter extends onceRequestFilter

* a. get token from request
* b. Vaidate token
* c. get username from token
* d. load user associated with token
* e. set authentication

``` Java

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        log.info("Header : {}", requestHeader);
        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                log.info("Illegal Argument exception");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                log.info("JWT Expired !!! ");
            } catch (MalformedJwtException e) {
                e.printStackTrace();
                log.info("Some changes has done in token !!! Invalid Token");
            } catch (Exception e) {
                e.printStackTrace();
                log.info("Exception !!!! ");
            }
        } else {
            log.info("Invalid header value !!!!!!!!!!!! ");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.info("Validate Token Failed !!!!! ");
            }
        }
        filterChain.doFilter(request,response);
    }
}


```

# 5 Create JwtRequest and JwtResponse

``` Java
public class JwtRequest
{
    private String email;
    private String password;

}

public class JwtResponse 
{
    private String jwtToken;
    private UserDto user;
}
```

# 6 Configure JWT in spring security config

``` JAVA

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

// ______________________________________________________________________________________________________

    // IN MEMORY CONFIGURATION
   @Bean
   public UserDetailsService userDetailsService(){
       UserDetails admin  = User.builder()
               .username("Nachiket")
               .password(passwordEncoder().encode("admin"))
               .roles("ADMIN")
               .build();
       UserDetails normal = User.builder()
               .username("Aniket")
               .password(passwordEncoder().encode("admin"))
               .roles("NORMAL")
               .build();
       //Users create
    //    InMemoryUserDetailsManager - Is implementation class of UserDetailsService

               return new InMemoryUserDetailsManager(admin,normal);
   }

    //FROM DB
// ______________________________________________________________________________________________________


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       //Form base Login
       http.authorizeRequests()
               .anyRequest().authenticated().and()
               .formLogin()
               .loginPage("login.html")
               .loginProcessingUrl("/process-url")
               .defaultSuccessUrl("/dashboard")
               .failureForwardUrl("/error")
               .and()
               .logout()
               .logoutUrl("/do-logout");
// ______________________________________________________________________________________________________

        http.csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .requestMatchers("/auth/login")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/users/")
                .permitAll()
                .requestMatchers(HttpMethod.GET,"/users")
                .permitAll()
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
// ______________________________________________________________________________________________________

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}



```

# 7 Create a Login api to create and return token if user is valid

``` java

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        this.doAuthenticate(request.getEmail(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtHelper.generateToken(userDetails);

        UserDto userDto = mapper.map(userDetails, UserDto.class);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .user(userDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadApiRequest(" Invalid username Or password !!!!!");
        }
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){
        String name = principal.getName();
        return new ResponseEntity<>(mapper.map(userDetailsService.loadUserByUsername(name), UserDto.class ), HttpStatus.OK);
    }

}


```

# 8 Test 