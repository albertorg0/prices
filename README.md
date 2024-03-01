# Prices

Technical test for Kairos DS selection process.

## Getting Started

### Prerequisites

- Java 17
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Build and Run

1. Clone the repository:

    ```bash
    git clone https://github.com/albertorg0/prices.git
    ```

2. Navigate to the project directory:

    ```bash
    cd prices
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The application will be accessible at [http://localhost:8080](http://localhost:8080).

## API Endpoints

### Get Price

- **Endpoint:** `/prices`
- **Method:** `GET`
- **Parameters:**
    - `applicationDate` (String): The date and time of the price application (e.g., "2020-06-14T10:00:00").
    - `productId` (Long): Identifier of the product.
    - `brandId` (Long): Identifier of the brand.
- **Response:**
    - JSON representation of the pricing information, including product and brand identifiers, price list, application
      dates, and final price.

## Running Tests

Run the following command to execute tests:

```bash
mvn test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework for building Java-based enterprise applications.
- [H2 Database](https://www.h2database.com/html/main.html) - In-memory database for development and testing.

## Authors

- Alberto Rojas

## License

This Project is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License).