# 🏦 Banking Application Backend - Spring Boot API

This is a *Bank Account Management System* built using *Spring Boot*. It provides RESTful APIs for user registration, login (with JWT authentication), and banking operations like creating accounts, deposits, withdrawals, and money transfers.


# 📌 Features

- ✅ User Registration with Role (Admin or Customer)
- 🔐 JWT-based Authentication & Authorization
- 👤 Admin-controlled Bank Account Creation
- 💵 Deposit and Withdraw Money
- 🔁 Transfer Funds Between Accounts
- 📜 Transaction History Tracking


# 📂 Tech Stack

- *Java 17*
- *Spring Boot*
- *Spring Security*
- *JWT (JSON Web Token)*
- *H2 / MySQL (pluggable DB)*
- *JPA/Hibernate*
- *Lombok*


# 🛠 Setup Instructions

1. *Clone the Repo:*
   bash
   git clone https://github.com/your-username/banking-app.git
   cd banking-app
   

2. *Run the Application:*
   - Using an IDE like IntelliJ or Eclipse, run BankingApplication.java.
   - Or use the command line:
     bash
     ./mvnw spring-boot:run
     

3. *Access H2 Console:*
   - URL: http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:testdb


# 🔐 Authentication Flow
1. Register (Admin or Customer)
*Endpoint:* POST /auth/register

json
{
  "username": "john_doe",
  "password": "secure123",
  "role": "CUSTOMER"
}



2. Login and Get JWT Token
*Endpoint:* POST /auth/login

json
{
  "username": "john_doe",
  "password": "secure123"
}




📥 *Response:*
json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}



Use this token in the Authorization header for all future requests:


Authorization: Bearer <token>
🧑‍💼 Admin APIs
➕ Create Bank Account
*Endpoint:* POST /admin/create-account

json
{
  "userId": 1,
  "accountType": "Savings",
  "initialBalance": 5000.00
}



# 👤 Customer APIs
> All customer endpoints require JWT token with CUSTOMER role.
💰 Deposit Money
*Endpoint:* POST /customer/deposit

json
{
  "accountId": 1,
  "amount": 2000.00
}



💸 Withdraw Money
*Endpoint:* POST /customer/withdraw

json
{
  "accountId": 1,
  "amount": 1000.00
}



🔁 Transfer Funds
*Endpoint:* POST /customer/transfer

json
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 500.00
}



📄 View Account Details
*Endpoint:* GET /customer/accounts/{id}
📜 View Transaction History
*Endpoint:* GET /customer/transactions/{accountId}
🧪 Testing

You can test the APIs using *Postman, **Thunder Client, or **Swagger UI* (if enabled).

# 🙋 Author

*Karella Pavani Vinay Kumar*  
Email: [vinaykarella12104@gmail.com](mailto:vinaykarella12104@gmail.com)  
LinkedIn: [vinay-karella-730a3628b](https://www.linkedin.com/in/vinay-karella-730a3628b)  
GitHub: [vinaykarella12104](https://github.com/vinaykarella12104)
