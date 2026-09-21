# Delay Stage Service

The Delay Stage Service manages delay information associated with logistics hubs.

It runs independently on port `7052`.

## Responsibilities

The service:

* Maintains delay stages for hubs
* Returns the current delay stage for a hub
* Publishes delay-stage information through ActiveMQ

Delay stages range from:

```text
0 - 8
```

## Port

```text
7052
```

Base URL:

```text
http://localhost:7052
```

## Run the Service

## Health Check

```bash
curl http://localhost:7052/health
```

Expected:

```text
OK
```

## Get Delay Stage

```http
GET /delay-service/{hubId}
```

Example:

```bash
curl http://localhost:7052/delay-service/H-501
```

Example response:

```json
{
  "hubId": "H-501",
  "stage": 3
}
```

## ActiveMQ

Delay-stage updates are published to:

```text
package-status-topic
```

Example message:

```json
{
  "hubId": "H-501",
  "stage": 3
}
```

The ActiveMQ broker runs on:

```text
tcp://localhost:61616
```

Transit subscribes to this topic.

## Data Flow

```text
Delay Stage
    ↓
Delay Stage Service
    ↓
ActiveMQ
    ↓
Transit Service
```
