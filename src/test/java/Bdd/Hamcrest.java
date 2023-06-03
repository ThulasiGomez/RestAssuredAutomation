package Bdd;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Hamcrest
{

    // Assertion Library used for Unit Testing
    // Human Redable
    // Provides decorators like 'is' and 'not'




    @Test
    void basicAssertion()
    {
        String payload="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given().
                baseUri("https://reqres.in/")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
                .post("/api/users")
        .then()
                .assertThat()
                .statusCode(201)
                .log().all()
                .body("name",equalTo("morpheus"),"leader",not("QA"));

    }

    @Test
    void basicAssertio()
    {
        String payload="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given().
                baseUri("https://reqres.in/")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
                .post("/api/users")
        .then()
                .assertThat()
                .statusCode(201)
                .log().all()
                .body("name",matchesPattern("[a-z]*"),"id",matchesPattern("[0-9]*"),
                        "job",matchesPattern("[a-z]{6}"));


    }

}

// [0-9] only numbers
// [^0-9] Except numbers
// [a-z] from a to Z
// [a -z] * from a to z can occur more than 1 time
// [a -z] ? from a to z can occur 0 - 1 time
// [a -z] + from a to z can occur 1 - more time
// [a -z]{length} from a to z check it has specified length
