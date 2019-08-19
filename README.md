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

    Access: worker, 2this client
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

    Access: worker
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
    
---

**Form for creating order**

*Request:*

    Access: client
    GET /api/v1/orders/creatingForm
    
*Response:*

Success

    {
        "tables": [
            {
                "id": 1,
                "capacity": 12,
                "freeStatus": true,
                "schedule": [
                    {
                        "begin": "08-08-2019 18:00",
                        "end": "08-08-2019 18:30"
                    },
                    ...
                ]
            },
            ...
        ],
        "dishes": [
                {
                    "id": 4,
                    "name": "Стейк из свинины",
                    "description": null,
                    "price": 25.0
                },
                ...
        ]
    }
    
-
        
**Creat order**

*Request:*

    Access: client
    POST /api/v1/orders
    {
    	"table": {
                "id": 5,
                "capacity": 12,
                "freeStatus": true
            },
    	 "dishesInOrder": [
                {
                    "dish": {
                        "id": 4,
                        "name": "Стейк из свинины",
                        "description": null,
                        "price": 25.0
                    },
                    "amount": 2
                }
            ],
        "bookingTimeBegin":"08-08-2019 18:00",
        "bookingTimeEnd":"08-08-2019 18:30"   
    }
    
*Response:*

Success

    status: 201
    
Failed

    {
        "message": "Begin of booking time can't be blank; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-

**All orders**

*Request:*

    Access: worker
    GET /api/v1/orders
    URL Param (not required, default = all) tableId={tableId}
    
*Response:*

Success

        [
            {
                "id": 65,
                "status": "NOT_TAKEN",
                "table": {
                    "id": 5,
                    "capacity": 12,
                    "freeStatus": true
                },
                "dishesInOrder": null,
                "client": {
                    "id": 2,
                    "name": "Борис",
                    "surname": "Бритва",
                    "email": "boris@britva.com",
                    "phoneNumber": "+375447784545",
                    "cardNumber": "3333-4444-5555"
                },
                "worker": null,
                "creationTime": "15-08-2019 10:00",
                "bookingTimeBegin": "15-08-2019 18:00",
                "bookingTimeEnd": "15-08-2019 18:30",
                "totalPrice": 0.00
            },
            ...
        ]
        
-

**Specific order**

*Request:*

    Access: client who made this order, worker
    GET /api/v1/orders/{id}
    
*Response*

Success:

    {
        "id": 60,
        "status": "NOT_TAKEN",
        "table": {
            "id": 5,
            "capacity": 12,
            "freeStatus": true
        },
        "dishesInOrder": [
            {
                "dish": {
                    "id": 4,
                    "name": "Стейк из свинины",
                    "description": null,
                    "price": 25.0
                },
                "amount": 3
            }
        ],
        "client": {
            "id": 2,
            "name": "Борис",
            "surname": "Бритва",
            "email": "boris@britva.com",
            "phoneNumber": "+375447784545",
            "cardNumber": "3333-4444-5555"
        },
        "worker": null,
        "creationTime": "14-08-2019 15:32",
        "bookingTimeBegin": "15-08-2019 18:00",
        "bookingTimeEnd": "15-08-2019 18:30",
        "totalPrice": 75.00
    }

Failed

    {
        "message": "There is no order with id = 1234",
        "httpStatus": "NOT_FOUND"
    }
    
-

**Delete order**

*Request:*

    Access: admin
    DELETE /api/v1/orders/{id}
    
*Response:*

Success

    status: 204
    
Failed

    {
        "message": "There is no order with id = 1234",
        "httpStatus": "NOT_FOUND"
    }   
    
-

**Set worker for specific order**

*Request:* 

    Access: worker
    PUT /api/v1/orders/active/{id}/worker
    
*Response:*

Success
    
    status: 200
    
Failed

    {
        "message": "Order 65 isn't active or doesn't exist",
        "httpStatus": "NOT_FOUND"
    }
or

    {
        "message": "Order already taken",
        "httpStatus": "BAD_REQUEST"
    }
    
-

**Update specific order (table, status, bookingTimeBegin, bookingTimeEnd)**

*Request:*

    Access: worker, client who made this order
    PUT /api/v1/orders/{id}
    {
        "id": 65,
        "status": "COMPLETED",
        "table": {
            "id": 5,
            "capacity": 12,
            "freeStatus": true
        },
        "creationTime": "21-08-2019 10:00",
        "bookingTimeBegin": "21-08-2019 18:00",
        "bookingTimeEnd": "21-08-2019 18:30"
    }
    
*Response:*

Success

    status: 200
    
Failed

    {
        "message": "Table 5 already booked in this period; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-

**Update dishes in a specific order**

*Request:*

    Access: client who made this order
    PUT /api/v1/orders/{id}/dishes
    [
        {
            "dish": {
                "id": 4,
                "name": "Стейк из свинины",
                "description": null,
                "price": 25.0
            },
            "amount":4
        },
        ...
    ]

*Response:*

Success

    status: 200
    
Failed

    {
        "message": "Order 65 isn't active or doesn't exist",
        "httpStatus": "NOT_FOUND"
    }
    
or

    {
        "message": "Amount dishes in order can't be negative or zero; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-

**Order for specific client**

*Request:*

    Access: worker, this client
    GET /api/v1/clients/{id}/orders
    URL Params status={all,active,not_active}
    
*Response:*

    order list
    
---

**Menu**

*Request:*

    Access: all
    GET /api/v1/menu
    
*Response*

     [
        {
            "id": 8,
            "name": "шаурма",
            "description": "курица, огурцы, помидоры, огурцы, чесночный соус, лаваш",
            "price": 4.0,
            "inMenu": true
        },
        ...
    ]    
             
-

**All dishes**

*Request:*

    Access: worker
    GET /api/v1/dishes
    
*Response:*

    [
        {
            "id": 8,
            "name": "шаурма",
            "description": "курица, огурцы, помидоры, огурцы, чесночный соус, лаваш",
            "price": 4.0,
            "inMenu": true
        },
        {
            "id": 6,
            "name": "Карбонара",
            "description": null,
            "price": 3.0,
            "inMenu": false
        },
        ...
    ]
    
-

**Specific dish from menu**

*Request:*

    Access: all
    GET /api/v1/menu/{id}
       
*Response:*

Success

    {
        "id": 4,
        "name": "Стейк из свинины",
        "description": null,
        "price": 25.0,
        "inMenu": true
    }   
    
Failed

    {
        "message": "There is no dish with id = 123",
        "httpStatus": "NOT_FOUND"
    }        
    
-

**Specific dish**

*Request:*
    
    Access: worker
    GET /api/v1/dishes/{id}
    
*Response:*

Success

    {
        "id": 6,
        "name": "Карбонара",
        "description": null,
        "price": 3.0,
        "inMenu": false
    }

Failed

    {
        "message": "There is no dish with id = 123",
        "httpStatus": "NOT_FOUND"
    }  
    
-

**Creat dish**

*Request:*

    Access: admin
    POST /api/v1/dishes
    {
    	"name":"new dish",
    	"description":"ingredients",
    	"price":14
    }
    
*Response:*

Success

    status: 201
    
Failed

    {
        "message": "Price can't be negative or zero; Name must be between 2 and 50 characters; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-

**Update specific dish**

*Request:*

    Access: admin
    PUT /api/v1/dishes/{id}
    {
    	"name":"new dish",
    	"description":"ingredients",
    	"price":14
    }  
    
*Response:*

Success 

    status: 200
    
Failed

    {
        "message": "Price can't be negative or zero; Name must be between 2 and 50 characters; ",
        "httpStatus": "BAD_REQUEST"
    }    
    
---

**All tables**

*Request:*

    Access: all
    GET /api/v1/tables
    
*Response:*

    [
        {
            "id": 5,
            "capacity": 12,
            "freeStatus": true
        },
        ...
    ]
    
-

**Specific table**

*Request:*

    Access: all
    GET /api/v1/tables/{id}
    
*Response:*

Success

    {
        "id": 1,
        "capacity": 6,
        "freeStatus": true
    }
    
Failed

    {
        "message": "There is no table with id = 1234",
        "httpStatus": "NOT_FOUND"
    }
    
-

**Create table**

*Request:*

    Access: admin
    POST /api/v1/tables
    {
    	"capacity":6
    }
    
*Response:*

Success
    
    status: 201
    
Failed

    {
        "message": "Capacity can't be negative or zero; ",
        "httpStatus": "BAD_REQUEST"
    }
    
-

**Update table**

*Request:*

    Access: admin
    PUT /api/v1/tables/{id}
    {
    	"capacity":6
    }    

*Response:*

Success 

    status: 200
    
Failed

    {
        "message": "Capacity can't be negative or zero; ",
        "httpStatus": "BAD_REQUEST"
    }     
    
---

**Create worker**

*Request:*

    Access: admin
    POST /api/v1/workers
    {
    	"name":"name",
    	"surname":"surname",
    	"email":"myemail@dfafd.com",
    	"password":"12345",
    	"confirmPassword":"12345",
    	"phoneNumber":"296665544",
    	"role":"WAITER",
    }
    
*Response:*

Success

    status: 201
    
Failed

    {
        "message": "User with this phone number already exist; User with this email already exist; ",
        "httpStatus": "BAD_REQUEST"
    }
    
**Staff**

*Request:*

    Access: worker
    GET /api/v1/workers/staff
    
*Response:*

    [
        {
            "id": 5,
            "name": "name",
            "surname": "surname",
            "email": "myemail@dfafd.com",
            "phoneNumber": "296665544",
            "role": "WAITER",
            "inStaff": true
        },
        ...
    ]

-

**All workers**

*Request:*

    Access: admin
    GET /api/v1/workers
    
*Response:*

    [
        {
            "id": 5,
            "name": "name",
            "surname": "surname",
            "email": "myemail@dfafd.com",
            "phoneNumber": "296665544",
            "role": "WAITER",
            "inStaff": true
        },
        {
            "id": 4,
            "name": "Гендольф",
            "surname": "Белый",
            "email": "gendolf@white.gray",
            "phoneNumber": "+375515454644",
            "role": "WAITER",
            "inStaff": false
        },
        ...
    ]
    
**Specific worker**

*Request:*

    Access: worker
    GET /api/v1/workers/{id}
    
*Response:*

Success
    
    {
        "id": 5,
        "name": "name",
        "surname": "surname",
        "email": "myemail@dfafd.com",
        "phoneNumber": "296665544",
        "role": "WAITER",
        "inStaff": true
    }
    
Failed

    {
        "message": "There is no worker with id = 123",
        "httpStatus": "NOT_FOUND"
    }
    
-

**Update worker except password**

*Request:*

    Access: admin
    PUT /api/v1/workers/{id}
    {
        "id": 5,
        "name": "name",
        "surname": "surname",
        "email": "myemail@dfafd.com",
        "phoneNumber": "296665544",
        "role": "WAITER",
        "inStaff": false
    }

*Response:*

Success

    status: 200
    
Failed

    {
        "message": "Incorrect phone number; ",
        "httpStatus": "BAD_REQUEST"
    }
    
**Update password for worker**

*Request:*

    Access: admin
    PATCH /api/v1/workers/{id}
    {
    	"email":"email@email.com",
    	"oldPassword":"1234",
    	"newPassword":"12345",
    	"confirmNewPassword":"12345"
    }
 
*Response:*

Success

    status: 200
    
Failed

    {
        "message": "Passwords dont match; ",
        "httpStatus": "BAD_REQUEST"
    }