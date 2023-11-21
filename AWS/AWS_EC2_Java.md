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
```
# Docker commands after docker hub push

- Pull Docker image from the docker hub   
```
docker pull phoenixfire16/reactapp:latest
docker pull phoenixfire16/springboot-example:latest
```
- Run docker image and create container    
```
sudo docker run -d -p 3000:3000 --name reactapp phoenixfire16/reactapp
sudo docker run -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=test --name springboot phoenixfire16/springboot-example
 ```

----------------------------------------------------
# Using .Jar file 
# Run Spring Boot JAR on AWS EC2 Instance

## 1. Build the JAR File

Build your Spring Boot project and create the JAR file. Include the test profile if needed.

```bash
mvn clean install -Dspring.profiles.active=test
```
## 2. Copy the JAR to EC2 Instance  
Copy your JAR file to the EC2 instance using scp or another method.
``` bash
scp -i /path/to/your/keypair.pem /path/to/your/app.jar ec2-user@your-ec2-instance-ip:/path/on/ec2/
```
## 3. SSH into EC2 Instance
Use SSH to connect to your EC2 instance.

``` bash
ssh -i /path/to/your/keypair.pem ec2-user@your-ec2-instance-ip

```
## 4. Install Java on EC2 Instance
Ensure that Java is installed on the EC2 instance.
``` bash
sudo yum install java

```
## 5. Run Your Spring Boot Application
Navigate to the directory where you copied your JAR file and run the application.
``` bash
java -jar your-app.jar --spring.profiles.active=test
```
## 6. Access Your Application
Open a web browser and navigate to your EC2 instance's public IP address or DNS name, along with the port your Spring Boot application is running on.

If any issues arise, check the application logs on the EC2 instance for error details.
