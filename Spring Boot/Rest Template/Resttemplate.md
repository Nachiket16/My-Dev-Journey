# Consume 3rd party API using the Rest template

## POST to API take the response 

``` Java 
@Service
public class TokenService 
{
    private String baseUrl;

    public Optional<Principal> verifyToken(String string ,String token) {

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        // Use Custom header according to need
        header.add("Content-Type", "application/json");
        header.add("TOKEN", token);

        HttpEntity<?> request = new HttpEntity<>(string, header);
        String url = baseUrl + "/path";

        ResponseEntity<Principal> responseEntity =
                new RestTemplate().exchange(url, HttpMethod.POST, request, Principal.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return Optional.of(responseEntity.getBody());
        }
        return Optional.empty();
    }
}
```

## GET from API 
> This eg is for get response were return is array of Object
> eg. Get student by ID

``` Java

 public Optional<Upstream> upstreamServiceId(Principal principal) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<MyClass> entity = new HttpEntity<MyClass>(headers);

        Long studentId = principal.getStudentId();
        String url = baseUrl + "/path/" + studentId;

        ResponseEntity<List<MyClass>> response =
            new RestTemplate().exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MyClass>>() {
            });
        List<MyClass> customClasses = response.getBody();
        MyClass responseEntity = customClasses.get(0);
 }

```