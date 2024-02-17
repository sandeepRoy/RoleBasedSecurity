
# Role Based Security - Demo 

## Requirements

1. To register users, as per roles - User, Manager & Admin

2. To generate a JWT Token, containing emailId as subject

3. User, Manager & Admin RUD as per below restrictions :

   A. Admin can have RUD access to itself, User and Manager :heavy_check_mark:

   B. Manager can have RU access to itself and User, only admin can Delete a manager 

   C. User can have RU access to itself, only admin can Delete a user :heavy_check_mark:




## Run Locally

Clone the project using - ```https://github.com/sandeepRoy/RoleBasedSecurity.git```

Create database schema with given sql file(role_security.sql).
Please make sure to create a blank schema named 'role_security' before importing the sql file.

To create the jar file, run inside the cloned folder
 
```
mvn clean install

```
Inside target folder, Run the jar file using

```
java -jar SecurityOne-0.0.1-SNAPSHOT.jar

```
To Test, use this Postman document

```
https://documenter.getpostman.com/view/12473629/2sA2r8145L

```


Thank You!

```
Sandeep Roy
Cell - 9178386506
Email - sandeep.roy2014@gmail.com
```
