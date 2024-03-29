# Installation 
## STEP 1: GET KAFKA  

Download the file from the link below
https://kafka.apache.org/quickstart

``` bash
tar -xzf kafka_2.13-3.4.0.tgz
cd kafka_2.13-3.4.0
```
______________________________
## STEP 2: START THE KAFKA ENVIRONMENT  

Kafka with ZooKeeper
Run the following commands in order to start all services in the correct order:

# Start the ZooKeeper service
``` bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```
Open another terminal session and run:

# Start the Kafka broker service
``` bash
bin/kafka-server-start.sh config/server.properties
```

Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.
_____________________________
## STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS  

Kafka is a distributed event streaming platform that lets you read, write, store, and process events (also called records or messages in the documentation) across many machines.

Example events are payment transactions, geolocation updates from mobile phones, shipping orders, sensor measurements from IoT devices or medical equipment, and much more. These events are organized and stored in topics. Very simplified, a topic is similar to a folder in a filesystem, and the events are the files in that folder.

So before you can write your first events, you must create a topic. Open another terminal session and run:

``` bash
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
```

All of Kafka's command line tools have additional options: run the kafka-topics.sh command without any arguments to display usage information. For example, it can also show you details such as the partition count of the new topic:

```bash
bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092

Topic: quickstart-events        TopicId: NPmZHyhbR9y00wMglMH2sg PartitionCount: 1       ReplicationFactor: 1	Configs:
    Topic: quickstart-events Partition: 0    Leader: 0   Replicas: 0 Isr: 0
```
____________________________________

## STEP 4: WRITE SOME EVENTS INTO THE TOPIC
A Kafka client communicates with the Kafka brokers via the network for writing (or reading) events. Once received, the brokers will store the events in a durable and fault-tolerant manner for as long as you need—even forever.

Run the console producer client to write a few events into your topic. By default, each line you enter will result in a separate event being written to the topic.

``` bash
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
```

This is my first event
This is my second event
You can stop the producer client with Ctrl-C at any time.
_______________________________________________
## STEP 5: READ THE EVENTS
Open another terminal session and run the console consumer client to read the events you just created:

```bash

bin/kafka-console-consumer.sh --topic topic_name --from-beginning --bootstrap-server localhost:9092

```
This is my first event
This is my second event
You can stop the consumer client with Ctrl-C at any time.

Feel free to experiment: for example, switch back to your producer terminal (previous step) to write additional events, and see how the events immediately show up in your consumer terminal.

Because events are durably stored in Kafka, they can be read as many times and by as many consumers as you want. You can easily verify this by opening yet another terminal session and re-running the previous command again
