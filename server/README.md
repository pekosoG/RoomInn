# RoomIn Server - Expenses Administrator
## Hackathon Challenge - GDL Batch 1 - Dev.F

---

## API Consumption

But first, these are the Current Endpoints:

- __/v1/roomie__:
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK
[
    {
        "id": 1,
        "name": "User",
        "email": "user@hotmail.com",
        "phone": "3333333",
        "photo": "photo.jpg",
        "password": "12345",
        "house_id": 1,
        "register_date": null,
        "update_date": null,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
]

POST [JWT NOT REQUIRED]
{
"roomie":{
      "name":"User",
      "email":"user@hotmail.com",
      "phone":"33333",
      "photo":"asdasdas.jpg",
      "password":"12345"
    }
}
RESPONSE: 200 OK
{
    "roomie": {
        "id": 1,
        "name": "User",
        "email": "user@hotmail.com",
        "phone": "33333",
        "photo": "asdasdas.jpg",
        "password": "12345",
        "updatedAt": "2017-11-08T07:41:01.567Z",
        "createdAt": "2017-11-08T07:41:01.567Z"
    }
}
```

- __/v1/roomie/:id__:
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK

{
    "roomie":{
        "id": 1,
        "name": "User",
        "email": "user@hotmail.com",
        "phone": "3333333",
        "photo": "photo.jpg",
        "password": "12345",
        "house_id": 1,
        "register_date": null,
        "update_date": null,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
}

PUT [JWT REQUIRED]
{
"roomie":{
      "name":"User",
      "email":"user@hotmail.com",
      "phone":"33333",
      "photo":"asdasdas.jpg",
      "password":"12345"
    }
}
RESPONSE: 200 OK
{
    "roomie": {
        "id": 1,
        "name": "User",
        "email": "user@hotmail.com",
        "phone": "33333",
        "photo": "asdasdas.jpg",
        "password": "12345",
        "updatedAt": "2017-11-08T07:41:01.567Z",
        "createdAt": "2017-11-08T07:41:01.567Z"
    }
}
```

- __/v1/roomie/byHouse/:house_id__:
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK
[
    {
        "id": 1,
        "name": "User",
        "email": "user@hotmail.com",
        "phone": "3333333",
        "photo": "photo.jpg",
        "password": "12345",
        "house_id": 1,
        "register_date": null,
        "update_date": null,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
]
``` 

- __/v1/house__ : 
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK
[
    {
        "name":"Hood ",
        "address":"Callle 3589",
        "photo":"Photo.jpg",
        "owner":1,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
]

POST [JWT REQUIRED]
{
"house":{
      "name":"Hood ",
      "address":"Callle 3589",
      "photo":"Photo.jpg",
      "owner":1
      }
}
RESPONSE: 200 OK
{
  "house":{
      "name":"Hood ",
      "address":"Callle 3589",
      "photo":"Photo.jpg",
      "owner":1,
      "createdAt": "2017-11-08T07:16:43.000Z",
      "updatedAt": "2017-11-08T07:16:43.000Z"
      }
}
```
- __/v1/house/:id__ :
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK

{
    "house":{
        "id":1,
        "name":"Hood ",
        "address":"Callle 3589",
        "photo":"Photo.jpg",
        "owner":1,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
}

PUT [JWT REQUIRED]
{
"house":{
      "name":"Hood ",
      "address":"Callle 3589",
      "photo":"Photo.jpg",
      "owner":1
      }
}
RESPONSE: 200 OK
{
  "house":{
      "name":"Hood ",
      "address":"Callle 3589",
      "photo":"Photo.jpg",
      "owner":1,
      "createdAt": "2017-11-08T07:16:43.000Z",
      "updatedAt": "2017-11-08T07:16:43.000Z"
      }
}
```

- __/v1/service__ : 
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK
[
    {
        "name":"Internet",
        "icon":"router-1.png",
        "cost":1000.00,
        "payment_due":15,
        "house_id":1,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
]

POST [JWT REQUIRED]
{
"service":{
      "name":"Internet",
      "icon":"router-1.png",
      "cost":1000.00,
      "payment_due":15,
      "house_id":1,
      }
}
RESPONSE: 200 OK
{
  "service":{
     "name":"Internet",
      "icon":"router-1.png",
      "cost":1000.00,
      "payment_due":15,
      "house_id":1,
      "createdAt": "2017-11-08T07:16:43.000Z",
      "updatedAt": "2017-11-08T07:16:43.000Z"
      }
}
```
- __/v1/service/:id__ : 
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK
{
    "service":{
        "name":"Internet",
        "icon":"router-1.png",
        "cost":1000.00,
        "payment_due":15,
        "house_id":1,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
}

PUT [JWT REQUIRED]
{
"service":{
      "name":"Internet",
      "icon":"router-1.png",
      "cost":1000.00,
      "payment_due":15,
      "house_id":1,
      }
}
RESPONSE: 200 OK
{
  "service":{
      "name":"Internet",
      "icon":"router-1.png",
      "cost":1000.00,
      "payment_due":15,
      "house_id":1,
      "createdAt": "2017-11-08T07:16:43.000Z",
      "updatedAt": "2017-11-08T07:16:43.000Z"
      }
}
```
- __/v1/service/byHouse/:house_id__ : 
```JSON
GET [JWT REQUIRED]
RESPONSE: 200 OK
[
    {
        "name":"Internet",
        "icon":"router-1.png",
        "cost":1000.00,
        "payment_due":15,
        "house_id":1,
        "createdAt": "2017-11-08T07:16:43.000Z",
        "updatedAt": "2017-11-08T07:16:43.000Z"
    }
]
```

For Authentication, we are __using JTW__. That means that __you need to get a token__ in order to consume our Endpoints.
- __/v1/auth__ : 
```JSON
POST
{
"roomie":{
      "email":"user@hotmail.com",
      "password":"12345"
      }
}

RESPONSE: 200 OK
{
 "status": true,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImlzcmFlbF9nYWhlQGhvdG1haWwuY29tIiwiaWQiOjEsImlhdCI6MTUxMDEyNTk3NSwiZXhwIjoxNTEwMTI3NDE1fQ.rj5tVWE52luoFvVItQqiVD19VwH7NuJ1fnq42_ddZq0"
}
```
After this, you need to send the token at the body or as a query variable or even as a header with the __x-access-token__ key.

All the Endpoints with __JWT REQUIRED__ will return __STATUS 402 FORBBIDEN__  if the token is missing.



__Models__

|House|
|------|
| id  |
| Name |
| Address |
| Photo |
| Owner |
| Register Date |
| Update Date |


|Roomie|
|------|
| id |
| Name |
| Email |
| Phone |
| Photo |
| House id |
| Password |
| Register Date |
| Update Date |

| Service |
|---------|
| id |
| Name |
| Icon |
| Cost |
| Payment Due |
| House id |
| Register Date |
| Update Date |

| Invite |
|--------|
| Email |
| House id |
| Register Date |
| Update Date |