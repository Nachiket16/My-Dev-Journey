# Dependency 
``` xml
  <dependency>
      <groupId>org.modelmapper</groupId>
      <artifactId>modelmapper</artifactId>
      <version>3.1.1</version>
  </dependency>
```

> Model Mapper is used to convert the DTO/Request body and map that value 
> to the specified entity. 

### If Column name is same then model mapper usually take the value for that entity.
> eg: id, anchorId this are 2 different value but mapper will put the value of anchorId into the id.      
> To avoid this we usually use the strict mode for the model mapper.

### **modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)**
modelMapper.map(transactionDataRequest, TransactionData.class);
