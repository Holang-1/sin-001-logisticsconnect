# Transit Service

The Transit Service calculates estimated arrival times between logistics hubs.

It runs independently on port `7053`.

## Responsibilities

The Transit Service:

* Retrieves hub information
* Estimates distance between hubs
* Calculates travel time
* Receives delay-stage information
* Calculates the final ETA

## Port

```text
7053
```

Base URL:

```text
http://localhost:7053
```

## Run the Service

The service connects to ActiveMQ when it starts.

## Dependencies

Transit communicates with:

### Hub Service

```text
http://localhost:7051
```

### ActiveMQ

```text
tcp://localhost:61616
```

### ActiveMQ Topic

```text
package-status-topic
```

## ETA Calculation

The estimated travel time is based on:

```text
Travel Time = Estimated Distance / 80 km/h
```

Delay is calculated using:

```text
Delay = (Origin Stage + Destination Stage) × 15 minutes
```

The final ETA is:

```text
ETA =
Current Time
+ Travel Time
+ Delay
```

## Example

For:

```text
Origin: H-501
Destination: H-507

Distance: 160 km

Origin Stage: 2
Destination Stage: 3
```

Travel time:

```text
160 / 80 = 2 hours
```

Delay:

```text
(2 + 3) × 15 = 75 minutes
```

Total estimated time:

```text
3 hours 15 minutes
```

## Main Components

| Component           | Responsibility                   |
| ------------------- | -------------------------------- |
| `HubClient`         | Communicates with Hub Service    |
| `DistanceEstimator` | Estimates distance               |
| `TimeEstimator`     | Converts distance to travel time |
| `DelayStageClient`  | REST client for delay stages     |
| `MqSubscriber`      | Receives ActiveMQ messages       |
| `ETA`               | Combines data and calculates ETA |
