# 🏦 BankProj
A basic banking REST API built with **Java, Spring Boot, and Maven**, featuring customer and account CRUD operations, customer-account relationships, deposits, withdrawals, and transactional transfers, with validation, centralized exception handling, PostgreSQL persistence using **JPA/Hibernate**, Http Basic Authentication using Spring Security, and Postman API testing.



## Features

* Customer CRUD operations
* Bank account CRUD operations
* Link accounts to customers
* Deposit and withdraw funds
* Transfer funds between accounts using database transactions
* Request validation
* Centralized exception handling
* PostgreSQL persistence with JPA/Hibernate
* HTTP Basic authentication
* Environment-based configuration


## Tech Stack

| Component   | Technology                  |
| ----------- | --------------------------- |
| Language    | Java                        |
| Framework   | Spring Boot 4.1.0           |
| Web         | Spring Web MVC              |
| Persistence | Spring Data JPA / Hibernate |
| Database    | PostgreSQL                  |
| Security    | Spring Security             |
| Validation  | Jakarta Bean Validation     |
| Build Tool  | Maven                       |
| API Testing | Postman                     |

## Architecture

The application follows a standard layered architecture:

```text
Client Request
      │
      ▼
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
PostgreSQL
```

* **Controller** — Handles HTTP requests and passes them to the service layer.
* **Service** — Contains business logic, validation, and transaction management.
* **Repository** — Handles database operations using Spring Data JPA.
* **Database** — PostgreSQL database accessed through Hibernate ORM.

Additional components handle cross-cutting concerns:

* **Security** — Protects endpoints using Spring Security and `SecurityFilterChain`.

## API Endpoints

### Customer API

| Method   | Endpoint                               | Description              |
| -------- | -------------------------------------- | ------------------------ |
| `POST`   | `/bank/customer/create`                | Create a customer        |
| `GET`    | `/bank/customer/{id}`                  | Get a customer by ID     |
| `GET`    | `/bank/customer/getAll`                | Get all customers        |
| `POST`   | `/bank/customer/update/{Id}/{newname}` | Update a customer's name |
| `DELETE` | `/bank/customer/delete/{Id}`           | Delete a customer        |

### Account API

| Method   | Endpoint                                             | Description                      |
| -------- | ---------------------------------------------------- | -------------------------------- |
| `POST`   | `/bank/account/saveAcc`                              | Create an account                |
| `GET`    | `/bank/account/{accnum}`                             | Get an account by account number |
| `GET`    | `/bank/account/AllAccounts`                          | Get all accounts                 |
| `PUT`    | `/bank/account/update/{accnum}/{balance}`            | Update an account balance        |
| `DELETE` | `/bank/account/{accnum}`                             | Delete an account                |
| `PUT`    | `/bank/account/connects/accId/{accId}/cusId/{cusId}` | Link an account to a customer    |
| `PUT`    | `/bank/account/deposit/{id}/{amount}`                | Deposit funds                    |
| `PUT`    | `/bank/account/withdraw/{id}/{amount}`               | Withdraw funds                   |
| `PUT`    | `/bank/account/transfer/{id1}/{id2}/{amount}`        | Transfer funds between accounts  |

## Validation & Exception Handling

The application uses **Jakarta Bean Validation** together with centralized exception handling.

### Validation

* Customer names cannot be blank.
* Customer emails cannot be blank and must follow a valid email format.
* Deposit, withdrawal, and transfer amounts must be greater than zero.
* Withdrawals and transfers require sufficient account balance.

### Exception Handling

Custom exceptions handle cases such as:

* Account not found
* Customer not found
* Insufficient account balance
* Invalid transaction amounts
* Request validation errors

`GlobalExceptionHandler` uses `@ControllerAdvice` to handle exceptions and return appropriate HTTP responses.

## Security

The API is secured using **Spring Security**.

* **Authentication:** HTTP Basic Authentication
* **Authorization:** All endpoints require authentication
* **CSRF:** Disabled for the REST API
* **Credentials:** Configured through environment variables
* **Passwords:** Not hard-coded in the source code

## Database

The project uses **PostgreSQL** with **Spring Data JPA and Hibernate** for persistence.

### Entity Relationship

```text
Customer
   │
   │ 1
   │
   │ N
   ▼
Account
```

A customer can have multiple accounts, while each account is associated with a customer.

Hibernate manages the database schema based on the JPA entity mappings.

## Transactions

Transfers between accounts are handled as a **database transaction** to ensure that the withdrawal and deposit are processed together.

If the transaction cannot be completed successfully, the database transaction can be rolled back to prevent an incomplete transfer.

## Testing

API functionality was tested during development using **Postman**.

Testing covered:

* Customer and account CRUD operations
* Customer-account linking
* Deposits and withdrawals
* Account transfers
* Input validation
* Exception handling
* Authentication
* HTTP status codes

## Project Structure

```text
src/main/java/com/bank/bankproj/
│
├── BankprojApplication.java
│
├── Config/
│   └── Security.java
│
├── Exceptionhandler/
│   └── GlobalExceptionHandler.java
│
├── Exceptions/
│   ├── AccountNotFoundException.java
│   ├── CustomerNotFoundException.java
│   └── InsufficientBalanceException.java
│
├── controller/
│   ├── AccountController.java
│   └── CustomerController.java
│
├── entity/
│   ├── Account.java
│   └── Customer.java
│
├── repository/
│   ├── AccountRepository.java
│   └── CustomerRepository.java
│
└── service/
    ├── AccountService.java
    └── CustomerService.java
```

## Environment Variables

The application uses environment variables for database and security credentials.

| Variable            | Purpose             |
| ------------------- | ------------------- |
| `DB_USERNAME`       | PostgreSQL username |
| `DB_PASSWORD`       | PostgreSQL password |
| `SECURITY_USERNAME` | HTTP Basic username |
| `SECURITY_PASSWORD` | HTTP Basic password |

Sensitive credentials are kept outside the source code.

## Getting Started

### Prerequisites

Make sure the following are installed:

* Java
* Maven
* PostgreSQL
* IntelliJ IDEA or another Java IDE

### Clone the Repository

```bash
git clone 
cd bankproj
```

### Configure Environment Variables

Set the following environment variables:

```text
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password
SECURITY_USERNAME=admin
SECURITY_PASSWORD=your_password
```

### Configure PostgreSQL

Make sure PostgreSQL is running and the database configuration in the project matches your local PostgreSQL setup.

### Run the Application

Open the project in **IntelliJ IDEA**, allow Maven to load the dependencies, and run:

```text
BankprojApplication.java
```

The application will start on:

```text
http://localhost:8080
```

## API Testing with Postman

1. Start the Spring Boot application.
2. Open Postman.
3. Select an API endpoint.
4. Go to **Authorization**.
5. Select **Basic Auth**.
6. Enter the configured `SECURITY_USERNAME` and `SECURITY_PASSWORD`.
7. Send the request and inspect the response.

## Author

**Seif Ashraf**

Computer Engineering Student
Backend Development — Java & Spring Boot
