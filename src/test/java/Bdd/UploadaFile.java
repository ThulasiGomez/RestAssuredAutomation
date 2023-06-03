package Bdd;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadaFile
{
    @Test
    void upload()
    {

        String attributes="{\"name\":\"temp.txt\",\"parent\":\t{\"id\":\"123456\"}}";
        given().
                baseUri("https://postman-echo.com")
                .multiPart("file",new File("src/test/resources/local.txt"))
                .multiPart("attributes",attributes,"application/json")
        .when()
                .post("/post")
         .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
