# Chess-Rest-Challenge

Sample chess restful-ish web service for a code challenge.

## Build



## Running locally
```
  git clone https://github.com/seancarroll/Chess-Rest-Challenge.git
  cd Chess-Rest-Challenge
  ./mvnw spring-boot:run
```

You can then access the web service at http://localhost:8080/

Can interact with the service via your browser or [Postman](https://www.getpostman.com/postman)

## Working with Eclipse

1) In the command line
```
git clone https://github.com/seancarroll/Chess-Rest-Challenge.git
```
2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven project
```
3) Run
```
Open ChessApplication.java and run main method (right click -> select "Run As" -> "Java Application")
```

## Architecture

Simple CQRS style command bus with feature folders. 
Requests (Queries) and commands are processed through a simple in-process [mediator](https://en.wikipedia.org/wiki/Mediator_pattern).
The mediator aka request dispathcher follows a simple "one model in, one model out" pattern in which it takes a request, can be either a command or query, delivers it to a handler, and hten returns the response back from the handler.
Request handlers are registered on start-up via an ApplicationListener via Spring's component scanning.
