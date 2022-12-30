# Microservices-complete-project

## Postman Requests
POST http://localhost:8025/student/
{
"name":"Nitin Rane",
"email": "nitin@gmail.com",
"address": "Kandivali",
"collegeId": 1
}

GET http://localhost:8025/student/3

POST http://localhost:8025/college/
{
"collegeName":"PD Lions",
"address":"Mumbai",
"collegeCode": 1
}

GET http://localhost:8025/college/1

## Hystrix Dashboard
http://localhost:8026/hystrix

## Gateway Hystrix Stream
http://localhost:8025/actuator/hystrix.stream

