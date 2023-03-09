# Installation 
## Step 1   

Download the file from the link below
https://kafka.apache.org/quickstart

``` bash
$ tar -xzf kafka_2.13-3.4.0.tgz
$ cd kafka_2.13-3.4.0
```
## Step 2   

Kafka with ZooKeeper
Run the following commands in order to start all services in the correct order:

# Start the ZooKeeper service
``` bash
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
Open another terminal session and run:

# Start the Kafka broker service
``` bash
$ bin/kafka-server-start.sh config/server.properties
```
Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.
