# *Confluent kafka on local machine guide 101*

# What is confluent ?
> Confluent Platform is a full-scale data streaming     platform that enables you to easily access, store, and manage data as continuous, real-time streams.

## Installation using docker

## Prerequisite 
> Docker        
>> Installation Guide (Linux)    
>``` bash
> sudo apt install docker
>```


> Docker Compose   
>> Installation Guide (Linux)   
>> https://phoenixnap.com/kb/install-docker-compose-on-ubuntu-20-04    

## Step 1 : Download and copy content of docker file:   
``` curl
  curl --silent --output docker-compose.yml \
  https://raw.githubusercontent.com/confluentinc/cp-all-in-one/7.3.2-post/cp-all-in-one/docker-compose.yml

```

## Step 2 : Start the Confluent Platform stack with the -d option to run in detached mode:

``` docker
sudo docker-compose up -d
```
output :
``` docker
Creating network "cp-all-in-one_default" with the default driver
Creating zookeeper ... done
Creating broker    ... done
Creating schema-registry ... done
Creating rest-proxy      ... done
Creating connect         ... done
Creating ksql-datagen    ... done
Creating ksqldb-server   ... done
Creating control-center  ... done
Creating ksqldb-cli      ... done
```

## Step 3: Verify that service is up and running:   
``` docker
docker-compose ps
```
output :
```  docker
     Name                    Command               State                Ports
------------------------------------------------------------------------------------------
broker            /etc/confluent/docker/run        Up      0.0.0.0:29092->29092/tcp,
                                                           0.0.0.0:9092->9092/tcp
connect           /etc/confluent/docker/run        Up      0.0.0.0:8083->8083/tcp,
                                                           9092/tcp
control-center    /etc/confluent/docker/run        Up      0.0.0.0:9021->9021/tcp
ksqldb-cli        /bin/sh                          Up
ksql-datagen      bash -c echo Waiting for K ...   Up
ksqldb-server     /etc/confluent/docker/run        Up      0.0.0.0:8088->8088/tcp
rest-proxy        /etc/confluent/docker/run        Up      0.0.0.0:8082->8082/tcp
schema-registry   /etc/confluent/docker/run        Up      0.0.0.0:8081->8081/tcp
zookeeper         /etc/confluent/docker/run        Up      0.0.0.0:2181->2181/tcp,
                                                           2888/tcp, 3888/tcp

```

If any container isn't up and running try again : sudo **docker-compose up -d** OR restart.
``` docker
sudo docker-compose restart control-center
```

## Step 4 : Navigate to Control Center at http://localhost:9021. It may take a minute or two for Control Center to start and load.

### ***Confluent Docs***
``` url
https://docs.confluent.io/platform/current/platform-quickstart.html#create-the-users-topic
```

Use the docs to navigate and explore more feature of confluent and kafka.