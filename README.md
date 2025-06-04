#
# Spring MVC Goals Tracker
#

## Overview

The Spring MVC Goals Tracker is a simple Java web application that allows users to create, view, update, and delete personal goals. Built using Spring MVC, this project utilizes Mockito and JUnit for unit testing, ensuring robust functionality and maintainability. I used an enum for the category and another for priority. It runs a tomcat 7 server and uses basic JSPs for the front-end. I used older technologies for the companies still using Java 8 and Spring MVC.

## Features

- Create new goals
- View a list of all goals
- Update existing goals
- Delete goals

## Technologies Used

- Java 8
- Spring Framework (Spring MVC)
- Spring Security
- Spring Test
- Mockito (for mocking objects in tests)
- JUnit (for testing)
- Maven (for project management)

## Project Structure

```
spring-mvc-goals
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── clotzer
│   │   │           ├── controller
│   │   │           ├── model
│   │   │           ├── security
│   │   │           └── service
│   │   ├── resources
│   │   │   └── log4j.properties
│   │   └── webapp
│   │       └── WEB-INF
│   │           ├── css
│   │           │   └── style.css
│   │           ├── views
│   │           │   ├── common
│   │           │   │   ├── footer.jspf
│   │           │   │   ├── header.jspf
│   │           │   │   └── navigation.jspf
│   │           │   ├── goal.jsp
│   │           │   ├── list-goals.jsp
│   │           │   └── welcome.jsp
│   │           ├── goal-setting-servlet.xml
│   │           └── web.xml
│   └── test
│       └── java
│           └── com
│               └── clotzer
│                   └── service
│
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- IDE (Eclipse, IntelliJ, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/clotzer/spring-mvc-goals.git
   cd spring-mvc-goals-tracker
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       └── java
│           └── com
│               └── clotzer
│                   └── goals
│                       ├── controller
│                       ├── service
│
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- IDE (Eclipse, IntelliJ, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/clotzer/spring-mvc-goals.git
   cd spring-mvc-goals-tracker
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application at `http://localhost:8080`.

### Running Tests

To run the unit tests, use the following Maven command:
```bash
mvn test
```

## API Endpoints

| Method | Endpoint               | Description               |
|--------|------------------------|---------------------------|
| GET    | /goals                 | Retrieve all goals        |
| GET    | /goals/{id}           | Retrieve a specific goal  |
| POST   | /goals                 | Create a new goal         |
| PUT    | /goals/{id}           | Update an existing goal   |
| DELETE | /goals/{id}           | Delete a goal             |

## Testing with Mockito and JUnit

This project includes unit tests for the service and controller layers using Mockito and JUnit. Example tests are located in the `src/test/java/com/example/goals` directory.

### Sample Test

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoalServiceTests {

    @MockBean
    private GoalRepository goalRepository;

    @Autowired
    private GoalService goalService;

    @Test
    public void testCreateGoal() {
        Goal goal = new Goal("Clean the garage");
        Mockito.when(goalRepository.save(goal)).thenReturn(goal);

        Goal createdGoal = goalService.createGoal(goal);
        assertEquals("Clean the garage", createdGoal.getName());
    }
}
