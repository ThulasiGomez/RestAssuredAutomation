package Bdd;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class JsonFromFile
{
    RequestSpecification req;
    @BeforeTest
    void requestSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com/")
                .addHeader("x-api-key", "PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .addHeader("Content-Type","application/json")
                .log(LogDetail.ALL);

        req=  requestSpecBuilder.build();

    }
    @Test
    void jsonFile()
    {
        File f=new File("payload.json");

        given(req).
                body(f)
        .when()
                .post("/workspaces")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("workspace.name",equalTo("Thulasi Ram"));

    }
}
