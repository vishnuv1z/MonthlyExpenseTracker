# Monthly Expense Tracker (Java + MySQL)

A simple Java console application created using Eclipse that helps you track your monthly expenses.  
You can add, view, update, and delete expenses.  
All data is saved in a MySQL database.

---

## 1. Features
- Add new income and expenses  
- View all income and expenses
- Update existing entries  
- Delete expenses
- view expense summary
- Stores all data in MySQL using JDBC
  
---

## 2. Set Up the Database (Do this first)

### Create the database:
```sql
CREATE DATABASE monthly_expense;
USE monthly_expense;
```

### Create the table:
```sql
CREATE TABLE expenses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  date DATE NOT NULL,
  category VARCHAR(100),
  description VARCHAR(255),
  amount DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 3. How to Run
1. Open the project in Eclipse  
2. Add the MySQL connector JAR to Build Path  
3. Make sure MySQL server is running  
4. Run `Main.java`

---

## Notes
- The project will not work unless the MySQL database is created and connected properly.
- Make sure the JDBC driver is added to the project.
