# Employee Management System (JavaFX + MySQL)

Role-based EMS with a login page, an **Admin portal** (manage employees, departments, attendance, leave approvals, salary reports) and an **Employee portal** (profile, attendance check-in/out, leave requests, salary view).

## Tech Stack
- Java 17, JavaFX 21 (FXML + CSS)
- MySQL 8 (JDBC via mysql-connector-j)
- Maven

## Setup

### 1. Database
Start MySQL and run the schema:
```bash
mysql -u root -p < src/main/resources/schema.sql
```

### 2. Configure credentials
Edit `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/ems
db.user=root
db.password=YOUR_MYSQL_PASSWORD
```

### 3. Run
```bash
mvn clean javafx:run
```
Requires JDK 17+ and Maven installed.

## Default Logins
| Role     | Username     | Password |
|----------|--------------|----------|
| Admin    | admin        | admin123 |
| Employee | john@ems.com | emp123   |

When the admin adds a new employee, a login is auto-created:
**username = employee's email, password = emp123** (employee can be given this to log in).

## Project Structure
```
src/main/java/com/ems/
├── MainApp.java          # JavaFX entry point -> login.fxml
├── config/               # DBConnection (reads db.properties)
├── model/                # User, Employee, Department, Attendance, LeaveRequest
├── dao/                  # JDBC CRUD (interface + Impl per entity)
├── service/              # AuthService, EmployeeService, AttendanceService, LeaveService, DepartmentService
├── session/              # SessionManager (logged-in user)
├── controller/           # LoginController + admin/ + employee/ FXML controllers
└── util/                 # PasswordUtil (SHA-256), InputValidator, SceneSwitcher

src/main/resources/com/ems/
├── fxml/                 # login + admin/ + employee/ views
└── css/styles.css
```

## Flow
```
Login -> AuthService (SHA-256 verify) -> SessionManager
      -> ADMIN    -> Admin Dashboard (sidebar swaps center pane)
      -> EMPLOYEE -> Employee Dashboard
```

## Notes
- Passwords are stored as SHA-256 hashes. For production use, switch to BCrypt.
- Deleting an employee cascades their login, attendance, and leave records (FK ON DELETE CASCADE).
- Attendance: one row per employee per day (UNIQUE constraint); Check In inserts, Check Out updates.
