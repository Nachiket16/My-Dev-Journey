### Mehtod for extracting the data from the encrypted payload using the cipher

``` Java
public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		try {
			String payload = "swn2AcvP9kE+E+nVjD4X9xhMtFXjo3nbA63jvSN/tJL10sZNlZ4X4xhgVSTxG+yM+OhFWgLr8k1YUsSAs3UomI/fTkIZjyu/SQ03oOaIncf+HY2vtHEnLL0cfarPOsbtny4nbWKNNKHytwfrpOLkBoxTVF4W8aRq3WeCNQurIOV5XHHsF9LeJp8k3NTCDwNJaItm5U6U7eBq+nKo4LJDy+uLH2CO8h+4FYrLWgEyXnD+XB1BRL+VkIq9CrIDP9BEraN5EcZQctZ0iO+pL+zAzwx7A2WFe+aRt8F5GYb/QW+wHbjxpDarMJxse2zlId6GqlodFkgRNsLy+3mk+RPZxCXN7kNrbDhIw1JYi4jAQFLVw9AEZ4G7aSsy0ph1jUM5cLYVaKpN8Ohx06s3QoPzG1VMNUJl8vk9hAdTqEAPKdZ0/I7L7gVB4Z4Ro7AXlyDjS5VV/fuF4HLzXNR3+dJl5Cct1ITXMt+sqeTupCs7nboKyG/7uvACo+6151M1uN8rRXKmFYgFpRRh4HHtxAvonw==";
			String key = "Mml2S52mvJ4UmMH9";
			final IvParameterSpec ivSpec = new IvParameterSpec(key.getBytes("UTF-8"));
			SecretKey secret = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);
			byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(payload));
			String output = new String(plainText);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
```
