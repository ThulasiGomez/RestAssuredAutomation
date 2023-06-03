package Bdd;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class StaticImportBdd
{

    @Test
    void run()
    {
        given().
                baseUri("https://reqres.in/").
        when()
                .get("api/users?page=2").
        then()
                .log().all();
    }

    @Test
    void validateResponse()
    {
        given().
                baseUri("https://reqres.in/").
        when()
                .get("api/user/2").
        then()
                .log().all()
                .body("data.id",equalTo(2));
    }


    @Test
    void extractResponse()
    {
       Response res= given().
                baseUri("https://reqres.in/").
        when()
                .get("api/user/2").
       then()
                .assertThat()
                .statusCode(200)
                .extract().response();


       System.out.println(res.body());

    }


    @Test
    void assertExtractResponse()
    {
        Object res= given().
                baseUri("https://reqres.in/").
        when()
                .get("api/user/2").
        then()
                .assertThat()
                .statusCode(200)
                .extract().response()
                .path("data.id");

        System.out.println(res);

        Assert.assertEquals(2,res);



    }
}
