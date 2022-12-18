# MARRS Bank
<hr>
<li>An Collabrative Project Consisting Of the 5 Developer Depicting the implementation of the payemnt wallet online transaction platform
<li>An developement of RESTful API for an Online Payment Wallet application. This API performs all the fundamental CRUD operations of any Online Wallet Banking platform with user validation at every step.
<br>
  
# ER Diagram
<hr>
The following Diagram depicts the flow of our Entity Relation Diagram to simplify the work flow.
<br>
<br>
  
  

![Screenshot (9)](https://user-images.githubusercontent.com/96742231/208314518-c9c6634b-a8e5-400a-a778-3e6ebce7a751.png)

<br>
<br>
<hr>
  
# Team Member Roles And Responsibilities
  
<hr>
<br>
<br>

1) Mazidul Islam  -Team Lead, Responsible for creating and implementing Transaction Module and Wallet Module.

2) Avanish Man Tripathi -Responsible for creating the Customer Module and Login/Logout Module. 

3) Sk Rakibuddin -Responsible for creating the Bank Account Module.

4) Rajni Kant Arya -Responsible for creating the Bill Payment Module.

5) Saurabh Kumar  -Responsible for creating the Beneficiary Module .

<br>
<br>

# Teach Stacks Implemented
<hr>
<br>
<br>
<li>Java
<li>Spring framework
<li>Spring Boot JPA
<li>Hibernate
<li>MySQL
<li>Swagger
<li>Lombok

  

<br>
<br>



# Modules
<hr>
<li>Login Logout User authentication
<li>Wallet
<li>BankAccount
<li>BeneficiaryDetails
<li>BillPayment
<li>Transaction

<br>
<br>

# Features
<hr>
<br>

- User Login authrntication
- validation for the account number
- validation for the current user and user identification
- RESTful API with CURD operations
- Functional Front End For better user experience

<br>
<br>

# Installation & Run
<hr>
<br>
<br>

```
#changing the server port
server.port=8888

#exception handling
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false

#db specific properties
spring.datasource.url=jdbc:mysql://localhost:3306/marrsbank
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER

```

<br>
<br>

# API Root Endpoint
<hr>
<br>
<br>

```
https://localhost:8888/
```

```
https://localhost:8888/swagger-ui/#
```
<br>
<br>


# Screenshots

![Screenshot (10)](https://user-images.githubusercontent.com/96742231/208315127-861e749e-903d-4325-a06b-bb60dc35e455.png)

![Screenshot (11)](https://user-images.githubusercontent.com/96742231/208315189-69396a14-28e7-4c2d-9064-38428d422772.png)

![Screenshot (12)](https://user-images.githubusercontent.com/96742231/208315125-105b27f7-5b85-4b99-9a10-e96d3fd6782f.png)

![Screenshot (13)](https://user-images.githubusercontent.com/96742231/208315126-36af5e00-08ae-4d79-a728-42dedf02887b.png)






 

