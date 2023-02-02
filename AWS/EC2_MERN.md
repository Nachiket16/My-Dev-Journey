- configuration

  - type: t2.micro
  - AMI: ubuntu 22.04
  - pem file: test-vm

- fix UNPROTECTED PRIVATE KEY FILE!

```bash

# change the permissions
> chmod 400 <pem file path>

# example
> chmod 400 ~/Downloads/test-vm.pem
```

- connect to the virtual machine

```bash
# command to connect to a virtual machine
> ssh -i <pem file path> ubuntu@<public ip address>

# example
> ssh -i ~/Downloads/test-vm.pem ubuntu@13.234.34.3	

```

## prepare the machine to host react website

```bash

# update the system cache
> sudo apt-get update

# install apache
> sudo apt-get install apache2

# install mysql
> sudo apt-get install mysql-server

# install nodejs
> curl -sL https://deb.nodesource.com/setup_16.x | sudo bash -
> sudo apt-get update
> sudo apt-get install nodejs

```curl --silent --location https://deb.nodesource.com/setup_12.x  | sudo bash -

- open port 80 from security settings
  - select the security tab
  - select security group
  - go to the inbound rules and click on the button "Edit inbound rules"
  - add a rule to open port 80 (http)
    - select Type as http (this will auto fill the port number with value 80)
    - select source to Anywhere-IPv4 (it will add 0.0.0.0/0)
  - click the button "Save rules"

## configure the mysql database

```bash

# login with root credentials
> sudo mysql

# change the root password
> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'manager';

# flush the settings
> FLUSH PRIVILEGES;

CREATE DATABASE Project;

# login with root user
# enter the password after pressing 'enter' key
>  

# execute all the sql commands to create database and required tables

```

## start the backend (express) server on aws remote server

- execute thse commands on local machine

```bash

# upload the server code (server code file path) to the server's home directory (~/)
> scp -i <pem file path> <server code zip file path> ubuntu@<ip address>:~/
> scp -i ~/Downloads/test-vm.pem server.zip ubuntu@3.110.77.127:~/

# login to the server
> ssh -i ~/Downloads/test-vm.pem ubuntu@13.233.42.124
```

- execute these commands on server

```bash

# install unzip utility
> sudo apt-get install unzip

# unzip the server code
> unzip server.zip

# execute the server to check if it can run successfully
> node server.js

# install pm2 utility to run the server in background
> sudo npm install -g pm2

```

- execute these commands on server (cloud machine) running ubuntu

```bash

# go to the express server cpde directory
> cd server

# start the server in background using pm2
> pm2 start server.js --name redbus-server

# show the list of servers running [which were started using pm2 command]
> pm2 list

# get more information about a selected server
> pm2 info <server id>

# stop the server
> pm2 stop <server id>

# restart the server
> pm2 restart <server id>

```

- open port 4000 from sercurity group
  - select the security tab
  - select security group
  - go to the inbound rules and click on the button "Edit inbound rules"
  - add a rule to open port 4000
    - select Type as 'Custom TCP'
    - enter port number as 4000
    - select source to Anywhere-IPv4 (it will add 0.0.0.0/0)
  - click the button "Save rules"

## upload the react website

- update the server ip address in the react code in config.js file
- build the react application

```bash

# execute this command on local machine
# build the application
> yarn build

```

- go inside the build directory
- archive all the files to a zip file
- upload the archived/zip file to the server

```bash

# execute this command on local machine
# use scp and upload the zip to the server
> scp -i <pem file> <archived/zip file> ubuntu@<ip address>:~/
> scp -i ~/Downloads/test-vm.pem Archive.zip ubuntu@13.234.34.3:~/

# login to the server
> ssh -i <pem file> ubuntu@13.233.42.124

```

- host the server using apache2

```bash

# move the react code zip file to /var/www/html
# as apache2 is by default configured with the /var/www/html directory
> sudo mv <zip file path> /var/www/html
> sudo mv ~/Final.zip /var/www/html

# go to the /var/www/html directory
> cd /var/www/html

# unzip the zip file
> sudo unzip images.zip

#MYSQL FILE UPLOAD
> mysql -u root -p Project < /home/ubuntu/file.sql
Password: *****

UPDATE ROLE
UPDATE User
SET Role='admin'
WHERE User_id = 1;

DONE !!!!

```
