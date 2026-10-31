# Smart Patient Monitoring System

## Project Overview
Smart Patient Monitoring System is an educational web application developed using Java and Spring Boot. It allows users to enter patient vital-sign measurements, view demonstration assessment flags, and maintain patient records using a local database.

**Note:** This is an educational demonstration project, not a real medical diagnostic or monitoring device.

## Features
- Enter patient name and vital-sign measurements.
- Record heart rate (BPM).
- Record body temperature (°C).
- Record blood oxygen level (SpO₂).
- Display demonstration NORMAL, WARNING, and EMERGENCY flags.
- View patient summary.
- Store patient measurements in an H2 database.
- View previously submitted patient records.
- Prevent duplicate submissions when refreshing the page.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 Database
- Maven
- JUnit 5
- Mockito
- Git and GitHub
- Jenkins

## Application Architecture
1. Thymeleaf provides the web interface.
2. Spring Boot handles application logic.
3. Spring Data JPA manages database operations.
4. H2 Database stores patient records.
5. JUnit and Mockito support automated testing.
6. Jenkins automates build and test execution.

## How to Run

### Prerequisites
- Java 17 or later
- Git (optional)

### Run using Maven

Clone the repository:

```bash
git clone https://github.com/Naveen-S-06/smart-patient-monitor.git
```

Navigate to the project directory:

```bash
cd smart-patient-monitor
```

Run the application:

```bash
mvnw.cmd spring-boot:run
```

Open the browser:

```text
http://localhost:8080
```

### Run using the packaged JAR

Download the JAR artifact from a successful Jenkins build and execute:

```bash
java -jar smart-patient-monitor-0.0.1-SNAPSHOT.jar
```

Then open `http://localhost:8080`.

## Automated Testing
The project includes automated tests using JUnit 5 and Mockito.

Jenkins supports three parameter-based test modes:
- NORMAL
- WARNING
- EMERGENCY

Latest verified results:
- Tests run: 6
- Failures: 0
- Errors: 0
- Skipped: 2

All three Jenkins scenario builds completed successfully.

## Jenkins CI Build
Jenkins is configured to:
1. Fetch source code from GitHub.
2. Compile the Java application.
3. Execute automated tests.
4. Package the Spring Boot application.
5. Archive the generated JAR artifact.
6. Publish the test report.

## Database
The application uses a local file-based H2 database.

Database URL:

```text
jdbc:h2:file:./data/smartpatientdb
```

## Important Medical Disclaimer
This application is developed strictly for educational purposes. Its demonstration flags are not medical diagnoses, clinical recommendations, or proof of patient safety. Never use this application to make real-world medical decisions.
