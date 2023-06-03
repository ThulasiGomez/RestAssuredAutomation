package Bdd;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class E2E
{
    int id;
    @Test(priority = 1)
    void createUser()
    {
        String payload="{\n" +
                "\t\"name\": \"Avani Ahuja\",\n" +
                "\t\"email\": \"ram11397@gmail.com\",\n" +
                "\t\"gender\": \"male\",\n" +
                "\t\"status\": \"active\"\n" +
                "}";
       Response res= (Response) given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
                .post("/public/v2/users")
        .then().
                log().all().
                statusCode(201)
                .extract().body();

        id =res.getBody().jsonPath().get("id");


      String  name  =res.getBody().jsonPath().get("name");

        Assert.assertEquals("Avani Ahuja",name);
        System.out.println(id);
    }

    @Test(priority = 2)
    void getUserDetails()
    {

        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
        .when()
                .get("/public/v2/users/"+id)
        .then().
                log().all().
                statusCode(200);

    }

    @Test(priority = 3)
    void UpdateUser()
    {
        String payload="{\n" +
                "\t\"name\": \"Msgr. Avani Devar\",\n" +
                "\t\"email\": \"rth@gmail.com\",\n" +
                "\t\"gender\": \"male\",\n" +
                "\t\"status\": \"active\"\n" +
                "}";
        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
        .put("/public/v2/users/"+id)
                .then().
                log().all().
                statusCode(200);
    }

    @Test(priority = 5)
    void deleteUser()
    {

        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
        .when()
                .delete("/public/v2/users/"+id)
        .then().
                log().all().
                statusCode(204);
    }

    @Test(priority = 4)
    void getUser()
    {

        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
        .when()
                .get("/public/v2/users/"+id)
        .then().
                log().all().
                statusCode(200);

    }
}
