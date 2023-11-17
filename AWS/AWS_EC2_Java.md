- Quick start : select os = Ubuntu
- Generate pem key for the connection
- launch instance with default free tier
- 
# Prepare machine for the CI/CD for the backend 

# update the system cache
> sudo apt-get update

# install apache
> sudo apt-get install apache2

```curl --silent --location https://deb.nodesource.com/setup_12.x  | sudo bash -

- open port 80 from the security settings
  - select the Security tab
  - select the security group
  - go to the inbound rules and click on the button "Edit inbound rules"
  - add a rule to open port 80 (http)
    - select Type as http (this will auto fill the port number with value 80)
    - select source to Anywhere-IPv4 (it will add 0.0.0.0/0)
  - click the button "Save rules"

