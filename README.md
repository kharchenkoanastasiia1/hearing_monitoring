# Hearing Monitoring Information System

A hearing-monitoring system designed around **three user roles: Patient, Doctor, and Administrator**. The broader system concept includes a patient-facing Android application for hearing tests and a Java/Spring MVC web application for medical review, communication, and account administration.

> **Project scope:** This repository contains the traditional **Spring MVC web/server component**, packaged as a WAR file. The Patient features below describe the wider Android + server system requirements, **not necessarily code implemented in this repository**. Individual workflows should be verified in the relevant codebase and running applications.

## Features and User Roles

### Patient — Android application (system requirements)

The patient-facing application is intended to support the following workflows:

- **User profiles:** register a profile with a nickname, age, and sex; edit profiles and switch between multiple users on the same device. Age and sex are used when selecting population-based hearing reference values.
- **Audio-device checks:** monitor connected headphones, including Bluetooth headsets, and verify the required volume setting before allowing a test to begin.
- **Hearing tests:** perform pure-tone threshold audiometry at specified frequencies and sound levels.
- **Audiograms and history:** display current and previous hearing-test results and retain a collection of audiograms.
- **Reference comparisons:** compare results with age-/sex-dependent population norms or the user's own personal norm. Initial tests use population norms.
- **Personal baseline:** calculate a personal norm using the **median of collected audiograms**. Automatic calculation starts after the third completed test and is finalized after the fifth; the requirements also allow additional recalculation when needed.
- **Local persistence:** store profile data, audiograms, and the personal median reference in a local database.
- **Server synchronization:** connect to the server and transmit hearing-test results when network connectivity is available so a doctor can review them.
- **Doctor messaging:** write messages to a doctor and read messages addressed to the patient.

These are **functional requirements for the patient role**. Their implementation status should be checked in the Android application; the Android source is not claimed to be part of this Spring MVC repository.

### Doctor

The doctor-facing web application is intended to support the following workflows:

- **Authentication:** sign in to the system to access doctor-specific functionality.
- **Assigned patients:** view a list of all patients associated with the signed-in doctor and select an individual patient for review.
- **Hearing-test history:** review the hearing-test results and audiograms of a selected patient.
- **Reference-based analysis:** inspect the patient's test results against both **personal** and **population** hearing reference values.
- **Progress over time:** examine changes in hearing-test results **for individual frequencies over time** to follow treatment dynamics.
- **Patient communication:** send a message to a specific patient, including recommendations to repeat a hearing test or consider adjustments to treatment.
- **Incoming messages:** read messages addressed to the authenticated doctor.
- **Server-mediated delivery:** exchange recommendations and messages with patients through the server.

These describe the **doctor role's business processes and functional requirements**; individual workflows should be verified against the web application's implementation.

### Administrator

The administrator-facing web application is intended to support **user-account management**, with access restrictions separating administrative operations from clinical data:

- **Account registration:** create new accounts for both **patients** and **doctors**.
- **Account updates:** edit the account information of existing patients and doctors.
- **Account deletion:** remove patient or doctor accounts from the system.
- **Password regeneration:** regenerate or reset a patient's or doctor's password when required.
- **Password confidentiality:** perform account administration **without being able to view users' passwords**.
- **Clinical-data separation:** **do not allow administrators to view patients' hearing-test results**; clinical review is reserved for the appropriate doctor-facing functionality.

These describe the **administrator role's business processes and functional requirements**. Password handling and authorization restrictions should be verified in the application's security configuration and implementation.

## Technology Stack

| Area | Technologies |
| --- | --- |
| Language | Java |
| Web framework | Spring MVC 5.2.9 |
| Security | Spring Security 5.4.1 |
| Persistence | Spring ORM, Hibernate, MySQL Connector/J, c3p0 |
| Web views | JSP, JSTL, Servlet API |
| Data and charts | Jackson, Chart.js |
| Other dependencies | AspectJ Weaver, Jakarta Mail |
| Build | Maven, WAR packaging |

The Maven compiler is configured with Java 9 source and target compatibility. The project uses the classic Spring MVC / servlet deployment model rather than an embedded Spring Boot server.

## Repository Layout

```text
hearing_monitoring/
├── src/
│   └── main/          # Application source and web resources
├── pom.xml            # Maven dependencies and WAR build configuration
├── .gitignore
└── README.md
```

## Build

### Prerequisites

- JDK compatible with the project's Java 9 compiler settings
- Apache Maven
- A compatible Java Servlet container for deploying the generated WAR
- MySQL, configured according to the application's persistence settings

Clone the repository and build from its root:

```bash
git clone https://github.com/kharchenkoanastasiia1/hearing_monitoring.git
cd hearing_monitoring
mvn clean package
```

The Maven configuration specifies the final artifact name `hearing_monitoring`, so a successful WAR build should produce:

```text
target/hearing_monitoring.war
```

### Deployment and database configuration

The repository uses WAR packaging, so the application must be deployed to a compatible servlet container rather than started with `java -jar`. Before deployment, inspect the application's Spring configuration and database connection settings, configure an available MySQL instance, and provide any required schema or seed data.

**Note:** Exact deployment steps, database initialization scripts, default accounts, and a verified local URL are not documented here because they have not been confirmed from the available repository information. The Maven dependency versions may also require compatibility checks before running on a modern Java/container stack.

## Access Control and Privacy

The documented role requirements distinguish among patient, clinical, and administrative responsibilities. Patients manage their own profiles and tests in the Android application; doctors review results and communicate with patients; administrators manage accounts and should not have access to patient test results. User passwords should not be displayed through administrative views.

This is an educational/portfolio project, not a certified medical device. Do not use real patient data without appropriate privacy and security controls.

## Project Context

The server component demonstrates practical work with a traditional Java web stack: Spring MVC, Spring Security, persistence with Hibernate/MySQL, Maven-based builds, and role-oriented application design.

## Author

**Anastasiia Kharchenko**  
GitHub: [@kharchenkoanastasiia1](https://github.com/kharchenkoanastasiia1)
