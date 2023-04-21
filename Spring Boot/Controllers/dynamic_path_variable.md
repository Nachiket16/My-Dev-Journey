# Unique Controller config cases      

## If path variable is not fix and we want to pass the diff payload in the service we can go with this method.    
In This method we have url which might take the path variable either with loan_application_id OR customer_id

``` JAVA
@GetMapping
    public ResponseEntity<String> paymentLinkGeneration(
            @RequestParam Map<String, String> requestParam) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();

      ResponseEntity<String> paymentLink = null;

      if (requestParam.get("customer_code") != null) {
            paymentLink =
                    paymentLinkService.getPaymentLink(
                            "customer_code", requestParam.get("customer_code"), principal);
        } else if (requestParam.get("loan_application_id") != null) {
            paymentLink =
                    paymentLinkService.getPaymentLink(
                            "loan_application_id",
                            requestParam.get("loan_application_id"),
                            principal);
        }

}
```
