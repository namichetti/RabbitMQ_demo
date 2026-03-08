# RabbitMQ Spring Boot Demo

## Description

This service demonstrates how to integrate **RabbitMQ with Spring Boot** to send and receive asynchronous messages.

The application exposes REST endpoints that allow publishing:

- Plain text messages
- JSON messages

Messages are sent to **RabbitMQ exchanges**, routed to **queues**, and consumed by a **RabbitMQ consumer** that logs the received messages.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring AMQP (RabbitMQ)
- Maven
- Lombok
- Docker

---

## Endpoints

Base path

```/api/v1```


---

## Publish Text Message

**Endpoint**


GET /api/v1/publish


**Example**


http://localhost:8080/api/v1/publish?message=HelloWorld


**Response**


Message published


---

## Publish JSON Message

**Endpoint**


POST /api/v1/json/publish


**Example Request**


POST http://localhost:8080/api/v1/json/publish


**Body**

```json
{
  "id": 1,
  "name": "John",
  "lastname": "Doe"
}```
Response

Message published
Running RabbitMQ with Docker

Run the following command:

``` docker run -d \
--hostname rabbitmq \
--name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
rabbitmq:3-management
RabbitMQ Management UI
http://localhost:15672 ```

From this interface you can monitor and manage RabbitMQ, including:

Queues

Exchanges

Bindings

Connections

Channels

Messages

**Default credentials**

```username: guest
password: guest```

**Running the Application**

Run the project using Maven:

```mvn spring-boot:run```
