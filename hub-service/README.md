# Hub Service

The Hub Service is responsible for providing logistics hub information to other services.

It runs independently on port `7051`.

## Responsibilities

The service provides information about:

* Hub ID
* Province
* Sorting centre
* Active/inactive status

The service acts as the source of hub information for other services.

## Port

```text
7051
```

Base URL:

```text
http://localhost:7051
```

## Run the Service

## Health Check

```bash
curl http://localhost:7051/health
```

Expected:

```text
OK
```

## Endpoints

### Get All Hubs

```http
GET /hub-service/hubs/
```

Example:

```bash
curl http://localhost:7051/hub-service/hubs/
```

### Get Hub by ID

```http
GET /hub-service/hubs/{id}
```

Example:

```bash
curl http://localhost:7051/hub-service/hubs/H-501
```

### Get Hubs by Province

```http
GET /hub-service/hubs/province/{province}
```

Example:

```bash
curl http://localhost:7051/hub-service/hubs/province/Gauteng
```

## Data Flow

```text
Hub Data
   ↓
Hub Service
   ↓
REST API
   ↓
Other Services
```

## Dependencies

The Hub Service does not need ActiveMQ to provide its REST endpoints.

Other services communicate with it using HTTP.