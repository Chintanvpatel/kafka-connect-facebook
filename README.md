This connector in initial development phase. The purpose of this source connector is to get data from facebook page.

Instructions for building
-------------------------
```
$ git clone https://github.com/Chintanvpatel/kafka-connect-facebook
$ (cd kafka-connect-facebook && ./gradlew build)
```

References
------------------------
1. https://github.com/wushujames/kafka-connector-skeleton
2. https://github.com/jcustenborder/kafka-connect-twitter
    
Instructions for running
------------------------
1.  Run zookeeper somehow.
    ```
    Here are instructions on how to run it using docker.
    $ docker run -d --name zookeeper -p 2181:2181 confluent/zookeeper
    ```

2.  Download and run kafka 0.9.0 locally by following the instructions at http://kafka.apache.org/documentation.html#quickstart
    ```
    $ curl -o kafka_2.11-0.9.0.0.tgz http://www.us.apache.org/dist/kafka/2.0.0/kafka_2.12-2.0.0.tgz
    $ tar xvfz kafka_2.12-2.0.0.tgz
    $ cd kafka_2.12-2.0.0
    $ ./bin/kafka-server-start.sh config/server.properties
    ```

3.  Run your connect-facebook-source plugin
    ```
    $ export CLASSPATH=/path/to/connect-facebook/build/libs/*
    $ kafka_2.11-0.9.0.0/bin/connect-standalone.sh kafka-connect-facebook/connect-standalone.properties  kafka-connect-facebook/connect-file-source.properties
    ```


