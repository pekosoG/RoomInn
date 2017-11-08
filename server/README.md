# RoomIn Server - Expenses Administrator
## Hackathon Challenge - GDL Batch 1 - Dev.F

---

__Models__

_House_
- id
- Name
- Address
- Photo
- Owner
- Register Date
- Update Date

```
{
"house":{
      "name":"Hood ",
      "address":"Callle 3589",
      "photo":"Photo.jpg",
      "owner":1
}
}
```

_Roomie_
- id
- Name
- Email
- Phone
- Photo
- House id
- Password
- Register Date
- Update Date

```
{
"roomie":{
      "name":"Someone",
      "email":"someone@hotmail.com",
      "phone":"33333333",
      "photo":"profile.jpg"
}
}
```

_Service_
- id
- Name
- Icon
- Cost
- Payment Due
- House id
- Register Date
- Update Date

_Invite_
- Email
- House id
- Register Date
- Update Date