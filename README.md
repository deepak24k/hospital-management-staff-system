# Hospital Management Staff
A very basic backend Application created with **Java**.

1: Also attached postman collection of APIs, for better understanding of Request APIs Data. 

### Requirements 🔧
* Java version 8 or higher.

### Installation 🔌
1. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:
2. Imported it in Eclipse or Intellij IDEA or any other Java IDE.
3. Connect Application with MySQL DB.
3. Run the application :D
4. Check in Postman with attaching Postman Collection APIs.

### Necessary Details 💡
1: Every APIs are secured with Authentication except, ('/signup', '/signin' and '/addMockDoctor'), import postman collection for better understanding of APIs

2 a>: Either execute mysql query in mysql DataBase, for getting all the tables data there 
			OR
2 b>: First run this API, so we have some Doctor list in our DataBase, **No-auth-required** in header.
	POST:
	http://localhost:8095/api/patient/addMockDoctor
	Request Body:
 	{
   	 "doctorNameList": [
        "Suresh",
        "Ramesh",
        "Suraj",
        "Anand",
        "Akhil",
        "Tarun",
        "Vikas",
        "Ranjan"
    	]
    }

3: After this, now we have to Admin signup account Hospital Staff member.
	POST:
	http://localhost:8095/api/authenticate/signup
	Request Body:
	{
    		"firstName": "Admin1",
    		"lastName": "dummy",
   		"email": "admin1@gmail.com",
    		"password": "Password",
    		"role": "admin",
    		"phoneNumber": "9876786545",
    		"dob": "1984-03-12",
    		"joiningDate": "2022-10-04"
	}

4: After calling above API, we will got token in respone, copy it and paste on each below list of 
   APIS, add in header with append 'Bearer ' at the begining of the value.
 
	Authorization : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdW1teUBnbWFpbC5jb20iLCJleHAiOjE2NTk4ODg0MTYsImlhdCI6MTY1OTg1OTYxNn0.NSKQ5bdyCQKFq19mFoQHxQxMwWplz83BwDoSxfbRElMru2CgKScSzOVphjNdWqAXjJzYPqYBlT7OXhVGC0DPCQ

	This auth has 8 hrs of session for accessing APIs. if expired, we can again generate from /login APIs.

5: Now we can call addPatient POST request APIs, will add auth token there for not getting forbidden error.
	URI: http://localhost:8095/api/patient/addPatient
	Request Body :
	{
    		"firstName": "Raj",
    		"lastName": "Kumar",
    		"email": "raj3345@gmail.com",
    		"age": 21,
    		"bloodGroup": "O -",
    		"doctorId": 4,
    		"dateOfAdmit": "2022-08-07",
    		"roomNo": 303,
		"expenses": 14136
	}

6: Now we can call other APIs also.
7: I have started this project in this weekend, so only add required data which is mention in doc., mostly focuses on 
   Authentication and token creation. 

   **There is DTO class User, Employee is extending that, lator in future we will implement staff, admin, accountant, doctor class that
    will extending User class.**


Thank-You.

