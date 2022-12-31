# E-store backend
## Prerequisites
Make, Docker, Maven, Java

To run the application follow below commands
```
make sql-init
make run
```

User API
```
Create user - POST: http://localhost:8080/user/register
```
```JSON
{
    "firstName": "test",
    "lastName" : "test",
    "username" : "test",
    "password" : "test",
    "email" : "test@test.com"
}
```
```
Login user - POST: http://localhost:8080/user/login
```
```JSON
{
    "username" : "test",
    "password" : "test"
}
```
```
Get all users - GET: http://localhost:8080/user/all
```
