# LogisticsConnect

LogisticsConnect is a Java-based logistics system built using independent services that communicate through REST APIs and ActiveMQ.

## Services

| Service             |    Port | Purpose                            |
| ------------------- | ------: | ---------------------------------- |
| Hub Service         |  `7051` | Provides logistics hub information |
| Delay Stage Service |  `7052` | Manages hub delay stages           |
| Transit Service     |  `7053` | Calculates estimated arrival times |
| ActiveMQ            | `61616` | Handles asynchronous messaging     |

## Architecture

```text
                 ┌─────────────────┐
                 │   Hub Service   │
                 │     :7051       │
                 └────────┬────────┘
                          │ REST
                          ▼
                 ┌─────────────────┐
                 │ Transit Service │
                 │     :7053       │
                 └────────┬────────┘
                          │
                 ┌────────┴────────┐
                 │                 │
                 ▼                 ▼
        Distance / ETA       ActiveMQ :61616
                                  ▲
                                  │
                           ┌──────┴──────┐
                           │ Delay Stage │
                           │   :7052     │
                           └─────────────┘
```

## Technologies

* Java
* Maven
* Javalin
* ActiveMQ
* JMS
* Jackson
* HTTP/REST
* Docker

## Prerequisites

```bash
java -version
mvn -version
docker --version
```

## Start ActiveMQ

Using Docker:

```bash
docker run -d --name activemq -p 61616:61616 -p 8161:8161 apache/activemq-classic
```

If the container already exists:

```bash
docker start activemq
```

Check:

```bash
docker ps
```

## Start the Services

Start each service from its own terminal.

- Ingestion Service

- Hub Service

- Delay Stage Service

- Transit Service

## Recommended Startup Order

## Health Checks

Ingestion Service:

```bash
curl http://localhost:7050/health
```

Hub Service:

```bash
curl http://localhost:7051/health
```

Delay Stage Service:

```bash
curl http://localhost:7052/health
```
Transit Service:

```bash
curl http://localhost:7053/health
```


Expected response:

```text
OK
```
