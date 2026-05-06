# Parking Management System (Java)

A simple Java Swing application for managing parking entries and exits with MySQL database integration.

## Features

- **Vehicle Entry**: Record vehicle parking with automatic timestamp
- **Vehicle Exit**: Check out vehicles by registration number
- **View All Vehicles**: Display all parking records with status

## Prerequisites

- Java 8 or higher
- MySQL Server
- MySQL Connector/J (included in lib/)

## Database Setup

Run these SQL commands in your MySQL client:

```sql
CREATE DATABASE IF NOT EXISTS parking_db;
USE parking_db;

CREATE TABLE IF NOT EXISTS parking_entries (
    entry_id INT PRIMARY KEY AUTO_INCREMENT,
    reg_no VARCHAR(20) NOT NULL,
    vehicle_type VARCHAR(30) NOT NULL,
    time_slot VARCHAR(50) NOT NULL,
    entry_date DATE NOT NULL,
    exit_date DATE DEFAULT NULL,
    status ENUM('IN','OUT') NOT NULL DEFAULT 'IN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## How to Run

1. Compile the Java files:
   ```bash
   javac -cp "lib/mysql-connector-j-9.6.0.jar;." *.java
   ```

2. Run the application:
   ```bash
   java -cp "lib/mysql-connector-j-9.6.0.jar;." MainUI
   ```

## Database Configuration

**Security Note:** Database credentials are currently hardcoded. For production use:

1. Copy `config.properties.example` to `config.properties`
2. Update with your actual database credentials
3. The `config.properties` file is automatically ignored by Git

Update `DBConnection.java` with your MySQL credentials:
- Default: `root` / `2514`
- Database: `parking_db`

## Project Structure

```
parking-management-system-java/
├── lib/
│   └── mysql-connector-j-9.6.0.jar
├── DBConnection.java
├── Vehicle.java
├── MainUI.java
└── README.md
```

## Security

- `.gitignore` excludes sensitive files like `config.properties`
- Database credentials should be moved to external config files
- Never commit passwords or API keys to version control

## License

This project is open source and available under the MIT License.