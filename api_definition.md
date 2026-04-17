# API-Definition M295
## Media
| URL | Method | Usecase | Request Body |
|------|-------|---------|--------------|
/library/media | GET | Get all media | -
/library/media?title= | GET | Get all medias with specific title | -
/library/media/{id} | GET | Get the media with specific id | -
/library/media | POST | Create a new media | the new media as JSON
/library/media/{id} | PATCH | Edit an existing media | the edited media as JSON
/library/media/{id} | DELETE | Delete a media | -

### JSON Structure
#### POST
````json
{
    "title": "",
    "author": "",
    "genre": "",
    "minAge": 12,
    "isbn": "",
    "locationCode": ""
}
````
Title and author need to be sent, everything else is optional
#### PATCH
````json
{
    "genre": "",
    "minAge": 12,
    "isbn": "",
    "locationCode": ""
}
````
All attributes are optional, but these are the only ones, that are allowed to be edited
## Address
| URL | Method | Usecase | Request Body |
|------|-------|---------|--------------|
/library/addresses | GET | Get all addresses | -
/library/addresses?address= | GET | Get all addresses with specific street and number | -
/library/addresses?zip= | GET | Get all addresses with specific zip | -
/library/addresses | POST | Create a new address | the new address as JSON
/library/addresses/{id} | DELETE | Delete an address (Fails if the address is still referenced by a customer) | -

### JSON Structure
#### POST
````json
{
    "address": "",
    "city": "",
    "zip": ""
}
````
Every attribute needs to be sent

## Customer
| URL | Method | Usecase | Request Body |
|------|-------|---------|--------------|
/library/customers/{id} | GET | Get customer with specific id | -
/library/customers?name= | GET | Get all customers with specific name | -
/library/customers?addressId= | GET | Get all customers with specific address | -
/library/customers | POST | Create a new customer | the new customer as JSON
/library/customers/{id} | PATCH | Edit an existing customer | the edited customer as JSON
/library/customers/{id} | DELETE | Delete a customer | -

### JSON Structure
#### POST
````json
{
    "firstName": "",
    "lastName": "",
    "birthDate": "YYYY-MM-DD",
    "email": "",
    "address": {
        "address": "",
        "city": "",
        "zip": ""
    }
}
````
Every attribute needs to be sent
#### PATCH
````json
{
    "email": "",
    "address": {
        "address": "",
        "city": "",
        "zip": ""
    }
}
````
All attributes are optional, but these are the only ones, that are allowed to be edited

## Borrowing
| URL | Method | Usecase | Request Body |
|------|-------|---------|--------------|
/library/borrowings | GET | Get all borrowings | -
/library/borrowings| POST | Create a new borrowing | the new borrowing as JSON
/library/borrowings/{id} | PATCH | Extend an existing borrowing | the edited borrowing as JSON
/library/borrowings?mediaId= | DELETE | End a borrowing | -

### JSON Structure
#### POST
````json
{
    "duration": 14,
    "customer": {
        "id": 3
    },
    "media": {
        "id": 12
    }
}
````
Duration is optional (has a default value), customer and media need to be sent.
#### PATCH
````json
{
    "duration": 28
}
````
Duration is the only attribute that is able to be edited