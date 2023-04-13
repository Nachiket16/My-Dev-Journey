### Custom JSON call Using the Interface JsonObjectBuilder

JsonObject object = Json.createObjectBuilder().build();
 
 
The class JsonBuilderFactory also contains methods to create JsonObjectBuilder instances. A factory instance can be used to create multiple builder instances with the same configuration. This the preferred way to create multiple instances. The example code below shows how to build a JsonObject model that represents the following JSON object:

``` JSON
{
	"app_name": "gitlab",
	"roles": [
		{
			"name": "comment-on-pr",
			"permissions": [
				[]
			]
		},
		{
			"name": "approve-pr",
			"permissions": [
				[
					"test1",
					"test2"
				]
			]
		}
	]
}
```
``` JAVA 
public class JsonResponseObject {

    public JsonObject getJsonResponse(Map<String, List<String>> map, String azpRole) {

        JsonObject payload = JsonArray.EMPTY_JSON_OBJECT;
        JsonObject roles = JsonObject.EMPTY_JSON_OBJECT;
        JsonArrayBuilder rolesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder permissionArrayBuilder = Json.createArrayBuilder();
        List<JsonObject> rolesList = new ArrayList<>();

        for (Map.Entry<String, List<String>> val : map.entrySet()) {
            List<String> permissions = val.getValue();
            for (String permission : permissions) {
                rolesArrayBuilder.add(permission);
            }
            payload =
                    Json.createObjectBuilder()
                            .add("name", val.getKey())
                            .add("permissions", Json.createArrayBuilder().add(rolesArrayBuilder))
                            .build();
            rolesList.add(payload);
        }

        for (JsonObject x : rolesList) {
            permissionArrayBuilder.add(x);
        }

        JsonObject appName =
                Json.createObjectBuilder()
                        .add("app_name", azpRole)
                        .add("roles", permissionArrayBuilder)
                        .build();
        return appName;
    }
}
```
_____________________________________________________________________________________________

 ``` JSON
 {
  "payment_gateway":{
  "invoice_details":[
  {
   "id":"123",
   "user_requested_amount":123
  }
  ],
  "loan_application_id":123,
  "source":"zxcv"
  }
}
```
 ``` JAVA
  JsonObjectBuilder root = Json.createObjectBuilder();
        JsonObject payload = Json.createObjectBuilder()
            .add("id", invoiceId)
            .add("user_requested_amount", order.getInvoiceAmount())
            .build();

        JsonObject invoiceDetails = root
            .add("invoice_details", Json.createArrayBuilder()
                .add(payload))
            .add("loan_application_id", order.getLoanApplicationId())
            .add("source", AppConstants.SOURCE)
            .build();

        JsonObject finalPayload = Json.createObjectBuilder()
            .add("payment_gateway", invoiceDetails)
            .build();

```
_____________________________________________________________________________________

 ``` JSON
{
     "firstName": "John", "lastName": "Smith", "age": 25,
     "address" : {
         "streetAddress": "21 2nd Street",
         "city": "New York",
         "state": "NY",
         "postalCode": "10021"
     },
     "phoneNumber": [
         { "type": "home", "number": "212 555-1234" },
         { "type": "fax", "number": "646 555-4567" }
     ]
 }
```
``` JAVA
 JsonBuilderFactory factory = Json.createBuilderFactory(config);
 JsonObject value = factory.createObjectBuilder()
     .add("firstName", "John")
     .add("lastName", "Smith")
     .add("age", 25)
     .add("address", factory.createObjectBuilder()
         .add("streetAddress", "21 2nd Street")
         .add("city", "New York")
         .add("state", "NY")
         .add("postalCode", "10021"))
     .add("phoneNumber", factory.createArrayBuilder()
         .add(factory.createObjectBuilder()
             .add("type", "home")
             .add("number", "212 555-1234"))
         .add(factory.createObjectBuilder()
             .add("type", "fax")
             .add("number", "646 555-4567")))
     .build();
```



### For custom request of the json object to the POST 
> In the given example we have the object

``` JAVA
//  use ObjectMapper object
    ObjectMapper mapper = new ObjectMapper();

// ERROR :  Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module \"com.fasterxml.jackson.datatype:jackson-datatype-jsr310\" to enable handling (through reference chain: com.mintifi.entities.AnchorReturn[\"due_date\"])",

    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
// ObjectNode will be for the custom root of the JSON object and will send the custom json to the post requst 
    ObjectNode root = mapper.createObjectNode();
    
// Base class for all JSON nodes, which form the basis of JSON Tree Model that Jackson implements.
//  One way to think of these nodes is to consider them similar to DOM nodes in XML DOM trees.
    JsonNode jsonNode = mapper.convertValue(myObj, JsonNode.class); // 
    
    JsonNode jsonNode = root.set("return", jsonNode);
    json = jsonNode.toPrettyString();
    //here we will get the json in String format
    log.info("Json ----> " + json);

```
> Eg:

``` JSON
"return" : {
    "a" : "asd",
    "b" : "asd",
    "c" : "asd"
}
  
```

### Object to the JSON String 

``` JAVA 

 String json;
try {
    ObjectMapper mapper = new ObjectMapper();
    // if object contains the time and date in that case use this it will 
    //ignore the Serialization error 
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    json = mapper.writeValueAsString(anchorInvoice);
} catch (JsonProcessingException e) {
    throw new RuntimeException(e);
}

log.info("##### postInvoice is successfully posted for the Anchor Invoice JSON: {}", json);



```
> Eg:
``` JSON
{
    "a" : "asd",
    "b" : "asd",
    "c" : "asd"
}
```

### String to JSON object

``` JAVA 
  JsonObject payload = Json.createObjectBuilder()
            .add("auth",
                Json.createObjectBuilder()
                    .add("refresh_token", anchorUpStream.getAuthRefreshToken())
                    .build()
            )
            .build();
            
// Dependancy --> <org.glassfish> <javax.json >
```
> Eg
> String str= "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozMzUsInR5cGUiOiJyZWZyZXNoX3Rva2VuIn0.l4X_-6d1tWVuzlLG1BueVe0fehyjjlo9M9ujk_ZeMcQ"
> Converted into 
``` JSON
{
    "auth": {
        "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozMzUsInR5cGUiOiJyZWZyZXNoX3Rva2VuIn0.l4X_-6d1tWVuzlLG1BueVe0fehyjjlo9M9ujk_ZeMcQ"
    }
}
```
### Get Value from JSON string 

``` JAVA

    public static String getValueFromJson(String node, String jsonBody) {
        String value = "";
        String[] splitNodes = node.split("\\.");
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(jsonBody);
            for (String i : splitNodes) {
                jsonNode = jsonNode.path(i);
            }
            value = jsonNode.asText();
        } catch (Exception e) {
            log.error("EXCEPTION", e);
        }
        return value;
    }
```
