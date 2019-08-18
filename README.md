##REST API DOCUMENTATION

**Authorization**

*Request:*


    Access: all
    GET /api/v1/users/login   
    {
    	"email":"putin@rf.ru",
    	"password":"1234"
    }

*Response:*

Success

    status: 200
    
    Token in headers

Failed

    {
        "message": "Invalid username or password",
        "httpStatus": "BAD_REQUEST"
    }

    
-

**Sign up for client**

*Request:*

    Access: all
    POST /api/v1/clients
    {	
    	"name":"json",
    	"surname":"n",
    	"email":"postman@email.com",
    	"password":"1234567",
    	"confirmPassword":"1234567",
    	"phoneNumber":"2320001212",
    	"cardNumber":"QQOQ EEEE TTTT 1234"
    }
 
*Response:*    

Success

    status: 201    
   
Failed

    {
        "message": "Incorrect phone number; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-
**Specified client**

*Request:*

    Access: for all workers and only for this client
    GET /api/v1/clients/{id}
    
*Response:*

Success

    {
        "id": 9,
        "name": "Artyom",
        "surname": "Nadyozhkin",
        "email": "artyom@gmail.com",
        "phoneNumber": "297777777",
        "cardNumber": "1111111111111111"
    }
Failed (invalid id)

    {
        "message": "There is no client with id = 123",
        "httpStatus": "NOT_FOUND"
    }
    
-

**List of clients**

*Request:*

    Access: only for workers
    GET /api/v1/clients

*Response:*

Success

    [
        {
             "id": 9,
             "name": "Artyom",
             "surname": "Nadyozhkin",
             "email": "artyom@gmail.com",
             "phoneNumber": "297777777",
             "cardNumber": "1111111111111111"
        },
        ...
    ]
    
-

**Update the client except password**

*Request:*

    Access: only this client
    PUT /api/v1/clients/{id}
    {
        "id": 2,
        "name": "Борис",
        "surname": "Бритва",
        "email": "boris@britva.com",
        "phoneNumber": "+375447784545",
        "cardNumber": "BVCX-4444-5555"
    }
    
*Response:*
    
Success   

    status: 200
    
Failed

    {
        "message": "Incorrect card number; Incorrect phone number; ",
        "httpStatus": "BAD_REQUEST"
    }    
    
-

**Update only client's password**

*Request*

    Access: only this client
    PATCH /api/v1/clients/{id}
    {
    	"email":"alex@pushka.ru",
    	"oldPassword":"12345678",
    	"newPassword":"4321",
    	"confirmNewPassword":"4321"
    }
    
*Response:*

Success

    status: 200

Failed

    {
        "message": "Passwords dont match; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-


        
