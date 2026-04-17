| Who | Requirement | HTTP Method |
| ----| ----------- | ----------- |
| The user | can list all media items | GET
| The user | can search media by title |GET
| The user | can retrieve a media item by ID | GET
| The user | can create a new media item | POST
| The user | can update an existing media item | PATCH
| The user | can delete a media item | DELETE
| The user | can list all addresses | GET
| The user | can search addresses by street and number | GET
| The user | can search addresses by ZIP code | GET
| The user | can create a new address | POST
| The user | can delete an address (only if not referenced) | DELETE
| The user | can retrieve a customer by ID | GET
| The user | can search customers by name |GET
| The user | can search customers by address ID | GET
| The user | can create a new customer | POST  
| The user | can update an existing customer | PATCH 
| The user | can delete a customer | DELETE
| The user | can list all borrowings | GET
| The user | can create a new borrowing | POST  
| The user | can extend an existing borrowing | PATCH 
| The user | can end a borrowing (by mediaId) | DELETE