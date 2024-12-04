This project is a vote session controller where users will be able to open vote sessions about an specific topic and then send votes to that topic. Once the session is closed, the votes will be counted and then published.


# Run requirements


- Java 21
- Docker
- RabbitMQ


## Installation


- Install JDK 21
- Install docker
- Run docker
- Run rabbitmq container



```bash
  docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
```

## API Reference

For more details, please access swagger UI.

#### Get all topics

```http
  GET /v1/topic
```

#### Create topic

```http
  POST /v1/topic
```

#### Open vote session

```http
  POST /v1/topic/open
```

#### Create associate

```http
  POST /v1/associate
```
#### Get all associates

```http
  GET /v1/associate
```

#### Register vote to a vote session

```http
  POST /v1/vote
```



## Acknowledgements

- Database access
 ```bash
  localhost:8080/h2-console
```

- API details
 ```bash
  localhost:8080/swagger-ui/index.html
```

