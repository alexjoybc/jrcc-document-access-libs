# jrcc-document-access-libs

This library provides a service to store documents using [redis](https://redis.io/) cache.

## Road Map

* [X] Store a document in redis cache
* [ ] Retrieve document from redis cache
* [X] Publish a document ready message to rabbitMq
* [ ] Subscribe when a document is ready from rabbitMq

## jrcc document access spring boot starter

This provide a spring boot starter for the document access lib using [redis](https://redis.io/)

### Usage

Add `jrcc-access-spring-boot-starter` to your project

```xml
<dependency>
    <groupId>ca.gov.bc.open</groupId>
    <artifactId>jrcc-access-spring-boot-starter</artifactId>
    <version>0.0.2-SNAPSHOT</version>
</dependency>
```

Add settings into `application.settings` file

```properties

# common spring boot settings

spring.redis.database=
spring.redis.host=
spring.redis.port=
spring.redis.password=
spring.redis.ssl=
spring.redis.timeout=
spring.redis.cluster.nodes=
spring.redis.sentinel.master=
spring.redis.sentinel.nodes=

# bc gov settings

bcgov.access.ttl= <-- cache time to live expressed in hours (default = 1)

```


## Sample App

The sample app is a demo that shows the usage of `jrcc-access-spring-boot-starter`

Run the application (using [docker](https://www.docker.com/))

Create a redis container

```bash
docker run --name some-redis -p 6379:6379 -d redis
```
Create a rabit container

```bash
docker run -d --hostname some-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

Install jrcc-access-libs

```bash
cd jrcc-document-access-libs
mvn install
```

Install jrcc-access-spring-boot-autoconfigure

```bash
cd jrcc-access-spring-boot-autoconfigure
mvn install
```

Install jrcc-access-spring-boot-starter

```bash
cd jrcc-access-spring-boot-starter
mvn install
```

Run the sample app

```bash
cd jrcc-access-spring-boot-sample-app
mvn spring-boot:run
```

You should get a similar output

```console
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.6.RELEASE)

2019-07-03 14:11:20.318  INFO 22912 --- [           main] JrccAccessSpringBootSampleAppApplication : Starting JrccAccessSpringBootSampleAppApplication on CAVICSR7LNQKC2 with PID 22912 (C:\github\jrcc-document-access-libs\jrcc-access-spring-boot-sample-app\target\classes started by 177226 in C:\github\jrcc-document-access-libs\jrcc-access-spring-boot-sample-app)
2019-07-03 14:11:20.324  INFO 22912 --- [           main] JrccAccessSpringBootSampleAppApplication : No active profile set, falling back to default profiles: default
2019-07-03 14:11:21.230  INFO 22912 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2019-07-03 14:11:21.238  INFO 22912 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data repositories in DEFAULT mode.
2019-07-03 14:11:21.294  INFO 22912 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 32ms. Found 0 repository interfaces.
2019-07-03 14:11:23.064  INFO 22912 --- [           main] JrccAccessSpringBootSampleAppApplication : Started JrccAccessSpringBootSampleAppApplication in 4.662 seconds (JVM running for 11.668)
2019-07-03 14:11:23.066  INFO 22912 --- [           main] eAppApplication$ApplicationStartupRunner : Starting access sample app
2019-07-03 14:11:23.133  INFO 22912 --- [           main] eAppApplication$ApplicationStartupRunner : content successfully stored in redis
2019-07-03 14:11:23.136  INFO 22912 --- [           main] eAppApplication$ApplicationStartupRunner : key: 9036f266-22b6-4136-8049-55ab33ab82c0
2019-07-03 14:11:23.137  INFO 22912 --- [           main] eAppApplication$ApplicationStartupRunner : MD5: 9CE2CBC8011718E747A86947FAB93F75
2019-07-03 14:11:23.219  INFO 22912 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: localhost:5672
2019-07-03 14:11:23.346  INFO 22912 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Created new connection: connectionFactory#556f0972:0/SimpleConnection@62569a6 [delegate=amqp://guest@127.0.0.1:5672/, localPort= 53343]
2019-07-03 14:11:23.394  INFO 22912 --- [           main] eAppApplication$ApplicationStartupRunner : Document Message Ready for Transaction with [jrcc-access-sample] on document type [test-doc], stored
with key [9036f266-22b6-4136-8049-55ab33ab82c0] successfully published
```

To view the message in a queue, login to [rabbitmq management console](http://localhost:15672) with default guest/guest and create a binding to the `document.ready` exchange using `test-doc` routing key

![binding](docs/document.ready.bind.png)