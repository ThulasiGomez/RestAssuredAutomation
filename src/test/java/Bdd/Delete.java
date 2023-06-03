package Bdd;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Delete
{
    @Test(priority = 1)
    void deleteUser()
    {

        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
        .when()
                .delete("/public/v2/users/1371163")
        .then().
                log().all().
                statusCode(204);
    }

    @Test(priority = 2)
    void getUser()
    {

        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
        .when()
                .get("/public/v2/users/1371163")
        .then().
                log().all().
               statusCode(404);

    }
}
