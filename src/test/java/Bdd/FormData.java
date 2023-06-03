package Bdd;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FormData
{
    @Test
    void form()
    {
        given().
                baseUri("https://postman-echo.com")
                .header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .multiPart("name","Ram")
                .formParam("name1","Hello")
        .when()
                .post("/post")
        .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
