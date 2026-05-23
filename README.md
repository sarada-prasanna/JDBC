# JDBC Development using MySQL & MongoDB

````md
# JDBC Development using MySQL & MongoDB

A complete Java database connectivity project using JDBC with MySQL and MongoDB integration. This project demonstrates CRUD operations, database connection handling, query execution, and NoSQL document storage using Java.

---

## 🚀 Technologies Used

- Java
- JDBC
- MySQL
- MongoDB
- Maven
- Eclipse / IntelliJ IDEA
- Git & GitHub

---

## 📌 Features

### MySQL JDBC
- Database Connection
- Create Table
- Insert Records
- Update Records
- Delete Records
- Fetch Data using SQL Queries
- PreparedStatement Usage
- ResultSet Handling

### MongoDB
- MongoDB Connection
- Insert Documents
- Update Documents
- Delete Documents
- Fetch Documents
- Collection Management

---

## 📂 Project Structure

jdbc-development/
│
├── src/
│   ├── mysql/
│   │   ├── DBConnection.java
│   │   ├── InsertData.java
│   │   ├── UpdateData.java
│   │   ├── DeleteData.java
│   │   └── FetchData.java
│   │
│   └── mongodb/
│       ├── MongoConnection.java
│       ├── InsertDocument.java
│       ├── UpdateDocument.java
│       ├── DeleteDocument.java
│       └── FetchDocument.java
│
├── lib/
├── README.md
└── pom.xml

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/jdbc-development.git
````

### 2️⃣ Open Project

Import the project into:

* Eclipse
* IntelliJ IDEA

---

## 🛢 MySQL Configuration

### Create Database

```sql
CREATE DATABASE jdbc_project;
```

### Update Database Credentials

```java
String url = "jdbc:mysql://localhost:3306/jdbc_project";
String username = "root";
String password = "your_password";
```

### MySQL JDBC Driver

Add MySQL Connector JAR or Maven dependency.

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

---

## 🍃 MongoDB Configuration

### Start MongoDB Server

```bash
mongod
```

### MongoDB Connection Example

```java
MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
MongoDatabase database = mongoClient.getDatabase("jdbcMongoDB");
```

### MongoDB Maven Dependency

```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>5.1.0</version>
</dependency>
```

---

## ▶️ Running the Project

1. Run MySQL Server
2. Run MongoDB Server
3. Execute Java files individually
4. Verify database records

---

## 📸 Output Examples

### MySQL Output

```text
Database Connected Successfully
Record Inserted Successfully
Data Retrieved Successfully
```

### MongoDB Output

```text
MongoDB Connected Successfully
Document Inserted Successfully
Document Retrieved Successfully
```

---

## 🎯 Learning Objectives

* Understand JDBC Architecture
* Perform CRUD Operations
* Learn SQL & NoSQL Integration
* Work with MySQL and MongoDB
* Manage Database Connectivity in Java

---

## 🧠 Future Enhancements

* Add Java Swing GUI
* Add Servlet & JSP Integration
* Add Hibernate ORM
* REST API using Spring Boot
* Authentication System

---

## 👨‍💻 Developed By

Sarada Prasanna Behera

---

## 📜 License

This project is created for educational and learning purposes.

```
```
