package Bdd;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpec
{

    // its a interface,Used when assertion to be done for several Request
    // It acheives by combinig common assertion into response specBuilder and using it for validation
    ResponseSpecification res;

    @BeforeTest(alwaysRun = false)
    void response()
    {
        res= RestAssured.expect().statusCode(200)
                .contentType(ContentType.JSON);
    }


    @BeforeTest()
    void responseBuild()
    {
        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .expectBody("name",equalTo("morpheus"))
                .log(LogDetail.ALL);

        res = responseSpecBuilder.build();
    }


    // Provides Builder to Create Response Specification
    // Its self exploratory method like expectStatus Code  etc to compare methods of Response Specification Interface


    @Test
    void run()
    {
        String payload="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
      given().
                baseUri("https://reqres.in/").
                body(payload)
                .header("Content-Type","application/json")
      .when()
                .post("/api/users")
      .then().
              spec(res).
              log().all();
    }
}
