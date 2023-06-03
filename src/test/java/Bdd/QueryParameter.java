package Bdd;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class QueryParameter
{

    @Test
    public void singleQueryParam()
    {
        given().
                baseUri("https://postman-echo.com")
                .param("foo","bar1")
                .queryParam("foo2","bar2")
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multipleQueryParam()
    {
        HashMap<String,String> queryParams=new HashMap<>();
        queryParams.put("foo","bar1");
        queryParams.put("foo2","bar2");
        given().
                baseUri("https://postman-echo.com")
                .queryParams(queryParams)
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multiValue()
    {
        given().
                baseUri("https://postman-echo.com")
                .queryParams("foo","bar1","foo1","bar2")
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


    @Test
    void pathParam()
    {
        given().
                baseUri("https://reqres.in")
                .pathParam("userId","2")
        .when()
                .get("/api/users/{userId}")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }


}
