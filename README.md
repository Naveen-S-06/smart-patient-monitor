# Smart Patient Monitoring System

## Project Overview
Smart Patient Monitoring System is a web-based mini project that allows users to enter patient vital-sign values and view a demonstration status.

## Features
- Patient name entry
- Heart rate input (BPM)
- Body temperature input (°C)
- Blood oxygen level (SpO₂) input (%)
- Demo warning and emergency flags
- Patient summary dashboard

## Technologies Used
- Java
- Spring Boot
- Thymeleaf
- Maven
- JUnit
- Git and GitHub
- Jenkins

## How to Run
1. Install Java and make sure it is available in your PATH.
2. Download the JAR file from the Jenkins build's Artifacts section.
3. Open Command Prompt in the folder containing the JAR file.
4. Run:

   `java -jar smart-patient-monitor-0.0.1-SNAPSHOT.jar`

5. Open `http://localhost:8080` in your browser.

## Testing
The project has 6 automated tests. The latest Jenkins build completed successfully with 0 test failures.

## Jenkins Build
Jenkins checks out the project from GitHub, runs Maven tests, packages the application, and archives the JAR artifact.

## Important Note
This is an educational demonstration only. The displayed flags are not medical diagnoses and must not be used to determine whether a real patient is safe.
