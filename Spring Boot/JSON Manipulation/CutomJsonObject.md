### For custom request of the json object to the POST 
> In the given example we have the obje

``` JAVA
//  use ObjectMapper object
    ObjectMapper mapper = new ObjectMapper();
    //This is in case if the module is throwing the error it will handle the exception 
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
// ObjectNode will be for the custom root of the JSON object and will send the custom json to the post requst 
    ObjectNode root = mapper.createObjectNode();
    
    // Base class for all JSON nodes, which form the basis of JSON Tree Model that Jackson implements. One way to think of these nodes is to consider them similar to DOM nodes in XML DOM trees.
    JsonNode jsonNode = mapper.convertValue(anchorReturn, JsonNode.class);
    
    JsonNode aReturn = root.set("return", jsonNode);
    json = aReturn.toPrettyString();
    log.info("Json ----> " + json);

```
