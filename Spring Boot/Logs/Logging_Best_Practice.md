### Spring boot logging best practice blog 
> https://coralogix.com/blog/spring-boot-logging-best-practices-guide/

> https://examples.javacodegeeks.com/enterprise-java/slf4j/slf4j-format-string-example/

> https://medium.com/@ankithahjpgowda/log-request-and-responses-of-rest-apis-in-springboot-c13f9bc7903f


### Standard logs for entire project 

> LOGGER CLASS
``` JAVA
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Logger {
    private HttpMethod method;
    private HttpStatusCode statusCode;
    private String url;
    private String requestBody;
    private String responseBody;
    
    public StringBuilder request(HttpMethod method, String url, String requestBody) {
        StringBuilder reqMessage = new StringBuilder();
        reqMessage.append("\nREQUEST ");
        reqMessage.append("method = [").append(method).append("]\n");
        reqMessage.append("path = [").append(url).append("]\n ");
        reqMessage.append("request body = [").append(requestBody).append("]\n ");
        return reqMessage;
    }
    
    public StringBuilder response(HttpStatusCode httpStatusCode, HttpMethod method, String url,
        String responseBody) {
        StringBuilder reqMessage = new StringBuilder();
        reqMessage.append("\nRESPONSE ");
        reqMessage.append("Status = [").append(httpStatusCode).append("]\n");
        reqMessage.append("method = [").append(method).append("]\n");
        reqMessage.append("path = [").append(url).append("]\n ");
        reqMessage.append("response body = [").append(responseBody).append("]\n ");
        return reqMessage;
    }

    public StringBuilder payload(Class className, String obj) {
        StringBuilder reqMessage = new StringBuilder();
        reqMessage.append("\nPayload ");
        reqMessage.append("Class Name = [").append(className).append("]\n");
        reqMessage.append("Object body = [").append(obj).append("]\n ");
        return reqMessage;
    }
}
```

> Use by using Slf4j

``` JAVA
@Service
@Slf4j
public class Service {
    @Autowired
    Logger logger;

    public void myMethod(){
        log.info("\nlog Request: {}",
            logger.request(HttpMethod.POST, url, request.getBody().toString()));
        log.info("\nlog Response: {}",
                    logger.response(responseEntity.getStatusCode(), HttpMethod.POST, url,
                        responseEntity.getBody().toString()));
    }
}
```

### Eg
``` Text
2023-02-22T17:28:21.195+05:30  INFO 29407 --- [nio-8080-exec-5] com.mintifi.security.TokenService        : 
log Request: 
REQUEST method = [POST]
path = [https://yoururl.com]
 request body = [{}]

2023-02-22T17:28:21.265+05:30  INFO 29407 --- [nio-8080-exec-5] com.mintifi.security.TokenService        : 
log Response: 
RESPONSE Status = [200 OK]
method = [POST]
path = [https://yoururl.com]
 response body = [Principal(token=c31198af-f79f-4250-b18e-2d3b2a09a599, date=2017, serviceId=3)]
 ```