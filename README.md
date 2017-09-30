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

## Architecture

Simple CQRS style command bus and feature folders
```