### For custom request of the json object to the POST 
> In the given example we have the obje

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
  { "a" : "asd",
    "b" : "sd",
    "c" : "sadf"
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
"return" : {
    "a" : "asd",
    "b" : "asd",
    "c" : "asd"
}
```
