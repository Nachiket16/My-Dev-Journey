### Extract data from the JSON 

> Following are example for the ResponseBody<String> from which we
> will extract exact value.

#### Dependency required
```Mavan
<!-- https://mvnrepository.com/artifact/javax.json/javax.json-api -->
<dependency>
    <groupId>javax.json</groupId>
    <artifactId>javax.json-api</artifactId>
    <version>1.1.4</version>
</dependency>

```
### Input JSON
``` JSON
Input Json File:
{
    "emp_id": 1017,
    "emp_name": "Nagesh Y",
    "emp_designation": "Manager",
    "department": "Java2Novice",
    "salary": 30000,
    "direct_reports": [
        "Nataraj G",
        "Kalyan",
        "Mahitha"
    ],
    "address": {
        "street":"MG Road",
        "city":"Bangalore"
    }
}
```
``` JAVA
package com.javaapi.json.examples;
 
public class ReadJsonExample {
 
    public static void main(String a[]){
         
        
        ResponseEntity<String> responseEntity = data;
        try {
            JsonReader reader = Json.createReader(new StringReader(responseEntity.getBody()));
            // Get the JsonObject structure from JsonReader.
            JsonObject empObj = reader.readObject();
            reader.close();
            // read string data
            System.out.println("Emp Name: " + empObj.getString("emp_name"));
            // read integer data
            System.out.println("Emp Id: " + empObj.getInt("emp_id"));
            // read inner json element
            JsonObject addrObj = empObj.getJsonObject("address");
            System.out.println("City: " + addrObj.getString("city"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```
``` JSON
Output:
Emp Name: Nagesh Y
Emp Id: 1017
City: Bangalore
```