package Bdd;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class JsonSchema
{
//    JSON Schema
//    Describes your existing data format.Clear,
//    human- and machine-readable documentation.
//    Complete structural validation, useful for automated testing.
//    Complete structural validation, validating client-submitted data.


        @Test
        void run()
        {
            given().
                    baseUri("https://reqres.in")
                    .log().all().
            when().
                    get("/api/users?page=2").
            then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("Get.json"));
        }
    }


