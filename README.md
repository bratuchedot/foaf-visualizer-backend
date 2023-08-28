# FOAF Visualizer
## Backend

This guide will walk you through the process of downloading **FOAF Visualizer** from GitHub and getting it up and running on your local machine.

## Prerequisites

- Git
- Java Development Kit (JDK) 17 or later
- Apache Maven

## Step 1: Cloning the Repository

1. **Open Terminal:** Launch your terminal or command prompt.
2. **Choose Directory:** Navigate to the directory where you want to clone the project. Use the `cd` command to move to the desired directory.
3. **Clone the Repository:** Run the following command to clone the repository from GitHub:
    ```sh
    git clone https://github.com/bratuchedot/foaf-visualizer-backend.git
    ```
4. **Navigate to Project Directory:** Move into the newly cloned project directory:
    ```sh
    cd foaf-visualizer-backend
    ```

## Step 2: Project Configuration

1. **Build the Project:** To build the Spring Boot project, run the following command:
    ```sh
    mvn clean install
    ```
2. **Run the Application:** Once the build is successful, run the application using the following command:
    ```sh
    mvn spring-boot:run
    ```
    This command will start the Spring Boot application. The application will be accessible at http://localhost:8080.

## Additional Notes
The application does't require a database setup. So you don't have to worry about setting up one. It only uses [Apache Jena](https://jena.apache.org/) to analyze the data.

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Git Documentation](https://git-scm.com/doc)

**Crafted with ♥ by Emilijan Koteski, August 2023**
