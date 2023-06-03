package Bdd;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class Filter
{

    @Test
    void run()
    {
        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.HEADERS))
        .when()
                .get("/get")
        .then()
                .statusCode(200);
    }

    @Test
    void logToFile() throws FileNotFoundException {
        PrintStream fp=new PrintStream(new File("restassured.log"));
        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.ALL,fp))
        .when()
                .get("/get")
        .then()
                .statusCode(200);
    }
}
