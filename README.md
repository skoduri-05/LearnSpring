# LearnSpring

LearnSpring is a small Spring Boot learning project built one commit and pull request at a time. The project starts with a generated Spring Boot application, adds a basic web endpoint and static view, then moves into configuration, dependency injection, dependency inversion, constructor injection, setter injection, and Spring-managed beans.

The current `main` branch includes the dependency-injection learning work from `LearnDI`, so the latest code focuses on a simple order/payment example.

## What the App Does

- Starts a Spring Boot application from `LearnSpringApplication`.
- Serves `src/main/resources/static/index.html` through `HomeController` at `/`.
- Reads `spring.application.name` from `application.properties`.
- Creates an `OrderService` bean.
- Injects a `PaymentService` implementation into `OrderService`.
- Calls `orderService.placeOrder()` at startup, which processes a sample `$100.00` payment.

At the moment, `PayPalPaymentService` is the Spring-managed `PaymentService` implementation. `StripePaymentService` remains in the codebase as a second implementation example, but it is not currently annotated as a Spring bean.

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Web
- JUnit 5 through `spring-boot-starter-test`

## Prerequisites

- JDK 21 available on your `PATH`
- `JAVA_HOME` pointing to a JDK 21 installation

## Project Structure

```text
src/main/java/com/skoduri7/learnspring
+-- LearnSpringApplication.java
+-- HomeController.java
+-- OrderService.java
+-- PaymentService.java
+-- PayPalPaymentService.java
`-- StripePaymentService.java

src/main/resources
+-- application.properties
`-- static/index.html
```

## Run Locally

From the project root:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Then open:

```text
http://localhost:8080/
```

You should see a simple "Hello World" page. The application also prints the configured application name when `/` is requested, and it prints the sample payment message during startup.

## Run Tests

```bash
./mvnw test
```

On Windows PowerShell:

```powershell
.\mvnw.cmd test
```

The current test suite contains a Spring context load test.

## Learning History

This README is based on the local git history, including merge commit bodies that preserve the pull request descriptions.

### Initial Spring Boot Project

The project began with the generated Spring Boot structure:

- Maven wrapper files
- `pom.xml`
- `LearnSpringApplication`
- `application.properties`
- a basic `contextLoads` test

This gave the repo a runnable baseline before any web or dependency-injection work was added.

### PR #1: LearnDependency

Branch: `LearnDependency`

Commit title: `build: add spring-boot-starter-web dependency`

The first learning PR added `spring-boot-starter-web`, turning the project from a plain Spring Boot app into one capable of serving web requests.

### PR #2: LearnController

Branch: `LearnController`

Commit title: `feat: add HomeController for basic web endpoint`

This PR introduced `HomeController`, the first controller in the project. It represents the controller layer in an MVC-style Spring application and maps the root route, `/`, to the view.

### PR #3: LearnView

Branch: `LearnView`

The PR description connects the new static HTML file to the "View" part of MVC. The project stores `index.html` under `src/main/resources/static`, which is appropriate for static content served directly by Spring Boot.

The description also calls out an open learning question: how dynamically rendered pages work when an application needs real-time updates. That question is left for a future stage, likely involving JavaScript, TypeScript, templates, or a frontend framework.

### PR #4: IntegratingAgents

Branch: `IntegratingAgents`

This PR added a local GitHub Copilot/IntelliJ agent for generating pull request titles in the Conventional Commits format.

The PR description explains why this matters even in a learning repo: small conventions help projects stay understandable as they grow. The agent is intentionally narrow in scope. It inspects a branch name and commit history, then proposes a PR title such as:

```text
feat: add HomeController for basic web endpoint
```

The final agent instructions live in `.github/agents/pr-agent.agent.md`.

### PR #5: LearnConfig

Branch: `LearnConfig`

This PR introduced use of `application.properties` through Spring's `@Value` annotation:

```java
@Value("${spring.application.name}")
private String appName;
```

The PR description explains that Spring Boot uses `src/main/resources/application.properties` as a central configuration file. It compares this idea to environment files used in other web frameworks, especially for values that differ between local development, QA, development servers, and production.

The description also includes a practical version-control lesson. A few lines of code were temporarily lost during branch and pull workflow, then recovered by using git history. The takeaway is that version control is not just for collaboration; it is also a safety net when local work disappears or branches get confusing.

### PR #6: LearnDI

Branch: `LearnDI`

This PR brought the dependency-injection lessons into `main`. The PR description frames dependency injection as a design pattern where a class receives the objects it needs instead of creating them itself. The goal is to reduce tight coupling between components.

The DI work progressed through these commits:

- `Our app wants to do some service, and we want to use an external serivce provider`
- `Programming Against Interfaces`
- `Constructor Injection`
- `Why DI matters`
- `Setter Injection`
- `Managing beans`
- `Multiple Constructors with beans`

Together, these commits move the code from directly using concrete service classes toward programming against the `PaymentService` interface. `OrderService` depends on the abstraction, while concrete implementations such as `PayPalPaymentService` and `StripePaymentService` handle the actual payment behavior.

The branch starts by adding `StripePaymentService` and `OrderService`, showing how an app might rely on an external payment provider. It then introduces the `PaymentService` interface so the order logic can depend on a contract instead of a specific provider. After that, the code walks through constructor injection, swapping from Stripe to PayPal, setter injection, and Spring-managed beans.

The branch also demonstrates Spring bean management:

- `@Service` marks classes that Spring should create and manage.
- `ApplicationContext` retrieves Spring-managed beans.
- Constructor injection supplies required dependencies.
- Setter injection appears as an additional injection style for comparison.
- Multiple constructors show how Spring chooses the constructor marked with `@Autowired`.

The PR description also includes a personal design note: dependency injection is useful when components may need to be swapped, tested independently, or decoupled, but it should still be applied thoughtfully instead of becoming the default answer for every class.

### Read Me!

The latest commit on `main` adds this README so the project history, PR descriptions, setup notes, and current Spring concepts are captured in one place.

## Current Learning Themes

- How Spring Boot projects are structured.
- How web dependencies enable controllers and static views.
- How MVC maps a request to a controller and then to a view.
- How application configuration is stored and injected.
- Why interfaces make service code more flexible.
- How Spring manages application objects as beans.
- How constructor injection differs from setter injection.
- How small development conventions, like Conventional Commits, can be supported by local agents.

## Notes

- `main` currently includes PR #6, `LearnDI`, followed by the `Read Me!` documentation commit.
- `LearnDI` remains available as the topic branch for the dependency-injection work.
- `backup/main-before-rewrite` preserves an earlier copy of the DI commit sequence before the current branch history was rewritten.
- The project is intentionally small and instructional. The code favors clear examples of Spring concepts over production completeness.
