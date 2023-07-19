# Helpful stream code snippet
- Sum by using Collectors Methods
``` JAVA
public static void main(String[] args) {
    List < Product > productsList = new ArrayList < Product > ();
    //Adding Products  
    productsList.add(new Product(1, "HP Laptop", 25000f));
    productsList.add(new Product(2, "Dell Laptop", 30000f));
    productsList.add(new Product(3, "Lenevo Laptop", 28000f));
    productsList.add(new Product(4, "Sony Laptop", 28000f));
    productsList.add(new Product(5, "Apple Laptop", 90000f));
    // Using Collectors's method to sum the prices.  
    double totalPrice3 = productsList.stream()
        .collect(Collectors.summingDouble(product -> product.getPrice()));
    System.out.println(totalPrice3);
}
```
- Find Max and Min Product Price
``` JAVA
public static void main(String[] args) {
    List < Product > productsList = new ArrayList < Product > ();
    // Adding Products
    productsList.add(new Product(1, "HP Laptop", 25000f));
    productsList.add(new Product(2, "Dell Laptop", 30000f));
    productsList.add(new Product(3, "Lenevo Laptop", 28000f));
    productsList.add(new Product(4, "Sony Laptop", 28000f));
    productsList.add(new Product(5, "Apple Laptop", 90000f));
    // max() method to get max Product price
    Product productA = productsList.stream()
        .max((product1, product2) -> product1.getPrice() > product2.getPrice() ? 1 : -1).get();

    System.out.println(productA.getPrice());
    // min() method to get min Product price
    Product productB = productsList.stream()
        .max((product1, product2) -> product1.getPrice() < product2.getPrice() ? 1 : -1).get();
    System.out.println(productB.getPrice());
}
```
- Convert List into Set
```JAVA
public static void main(String[] args) {
    List < Product > productsList = new ArrayList < Product > ();

    // Adding Products
    productsList.add(new Product(1, "HP Laptop", 25000 f));
    productsList.add(new Product(2, "Dell Laptop", 30000 f));
    productsList.add(new Product(3, "Lenevo Laptop", 28000 f));
    productsList.add(new Product(4, "Sony Laptop", 28000 f));
    productsList.add(new Product(5, "Apple Laptop", 90000 f));

    // Converting product List into Set
    Set < Float > productPriceList = productsList.stream().filter(product -> product.getPrice() < 30000)
        .map(product -> product.getPrice()).collect(Collectors.toSet());
    System.out.println(productPriceList);
}
```
- Convert List into Map
```JAVA
public static void main(String[] args) {
    List < Product > productsList = new ArrayList < Product > ();

    // Adding Products
    productsList.add(new Product(1, "HP Laptop", 25000 f));
    productsList.add(new Product(2, "Dell Laptop", 30000 f));
    productsList.add(new Product(3, "Lenevo Laptop", 28000 f));
    productsList.add(new Product(4, "Sony Laptop", 28000 f));
    productsList.add(new Product(5, "Apple Laptop", 90000 f));

    // Converting Product List into a Map
    Map < Integer, String > productPriceMap = productsList.stream()
        .collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));
    System.out.println(productPriceMap);
}
```
- Using Method References in Stream Examples
```JAVA
public static void main(String[] args) {

    List < Product > productsList = new ArrayList < Product > ();

    // Adding Products
    productsList.add(new Product(1, "HP Laptop", 25000 f));
    productsList.add(new Product(2, "Dell Laptop", 30000 f));
    productsList.add(new Product(3, "Lenevo Laptop", 28000 f));
    productsList.add(new Product(4, "Sony Laptop", 28000 f));
    productsList.add(new Product(5, "Apple Laptop", 90000 f));

    List < Float > productPriceList = productsList.stream()
        .filter(p -> p.getPrice() > 30000) // filtering data 
        .map(Product::getPrice) // fetching price by referring getPrice method
        .collect(Collectors.toList()); // collecting as list
    System.out.println(productPriceList);
}
```
