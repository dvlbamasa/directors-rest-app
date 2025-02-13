# Lime-Pay Backend Dev Challenge
Directors - Rest API Application (Spring Boot Solution)

##  Implementation

- Used Java 17 and Spring Boot v3.3.3
- Used Spring Cloud Openfeign for a declarative web REST client
- Used Lombok library to reduce boilerplate codes
- Added simple unit tests using Mockito

### Logic behind the data processing:
```
        List<MovieDataDto> allMovies = new ArrayList<>();
        int page = 1;

        ClientResponseDto responseDto = wireMockClient.getMoviesByPage(page);
        allMovies.addAll(responseDto.getData());
        int totalPages = responseDto.getTotalPages();
        page++;

        while (page <= totalPages) {
            ClientResponseDto response = wireMockClient.getMoviesByPage(page);
            allMovies.addAll(response.getData());
            page++;
        }
```
-Called the wire mock API based on the value of the totalPages. and fetched all Movie data and stored it in an ArrayList

```
List<String> directors = allMovies.stream()
                .collect(Collectors.groupingBy(MovieDataDto::getDirector, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
```
- Performed stream operations to achieve the required result of getting the Directors' name with count greater than the threshold; sorted alphabetically

##  Setup
Below are **three** different options on how to run the application.

**Pre-requisites**

- You should have **Java 17 JDK** and **Maven** installed on your machine
- Cloned the repository to your local machine


### 1. Run directly from source code:


a. Go to the root directory of your project

b. Run the script:

`mvn spring-boot:run`

### 2. Run a packaged JAR file

a. Build the JAR File - First, make sure you have built your Spring Boot application into a JAR file using the command:

`mvn clean package`

b. Run the JAR File - Use the command:

`java -jar path/to/your-application.jar`

### 3. Via Intellij Idea IDE

a. On the upper right section, select the Maven button (M) then click the **Reload All Maven Project** button to download dependencies and load the project

b. Select the DirectorsApplication.class on the project directory

c. Simply click the Run button on the left side of the class name.

*Note: make sure that the JDK version of your Run/Debug Configuration is at least java 17*

## Test

To test the REST API you can use POSTMAN.



### Get Directors by threshold

Method: **GET**

Endpoint: `localhost:8080/api/directors?threshold=1`

ResponseBody:
```
{
    "directors": [
        "Clint Eastwood",
        "M. Night Shyamalan",
        "Martin Scorsese",
        "Pedro Almodóvar",
        "Quentin Tarantino",
        "Woody Allen"
    ]
}
```
