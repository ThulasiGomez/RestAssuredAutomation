package Bdd;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;

public class Post
{
    @Test
    void validatePost()
    {
        String payload="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given().
                baseUri("https://reqres.in/")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
                .post("/api/users")
        .then()
                .assertThat()
                .statusCode(201)
                .log().all();
    }

    @Test
    void post_Non_Bdd()
    {
        String payload="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

       Response res= with().
                baseUri("https://reqres.in/").
                body(payload)
                .header("Content-Type","application/json")
                .post("/api/users");

       System.out.println(res.body().prettyPrint());
        System.out.println(res.headers());
        System.out.println("Status Code: "+res.statusCode());
        Assert.assertEquals(res.statusCode(),201);
    }


    @Test
    void post_with_Authentication()
    {

        String payload="{\n" +
                "\t\"name\": \"Avani Ahuja\",\n" +
                "\t\"email\": \"ram1307@gmail.com\",\n" +
                "\t\"gender\": \"male\",\n" +
                "\t\"status\": \"active\"\n" +
                "}";
        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
                .post("/public/v2/users")
        .then().
                log().all().
                statusCode(201);
    }

    @Test
    void put_with_Authentication()
    {

        String payload="{\n" +
                "\t\"id\": 1267464,\n" +
                "\t\"name\": \"Msgr. Avani Devar\",\n" +
                "\t\"email\": \"rthh@gmail.com\",\n" +
                "\t\"gender\": \"male\",\n" +
                "\t\"status\": \"active\"\n" +
                "}";
        given().
                baseUri("https://gorest.co.in/").
                auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162")
                .header("Content-Type","application/json")
        .when()
                .body(payload)
                .put("/public/v2/users/1267464")
        .then().
                log().all().
                statusCode(200);
    }

    @Test
    void nested_Json_Object()
    {
        HashMap<String, Object> mainObj=new HashMap<String,Object>();

        HashMap<String, String> nestedObj=new HashMap<String,String>();
        nestedObj.put("name","Hello Test");
        nestedObj.put("type","personal");
        nestedObj.put("Description","Rest Assured Created This");

        mainObj.put("workspace",nestedObj);

        given().
                baseUri("https://api.postman.com/")
                .header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .header("Content-Type","application/json")
                .body(mainObj)
                .log().all()
        .when()
                .post("/workspaces")
                .then()
        .log().all()
                .assertThat().statusCode(200)
                .body("workspace.name",equalTo("Hello Test"));

    }

    @Test
    void json_array_payload()
    {
        HashMap<String, String> obj1=new HashMap<String,String>();
        obj1.put("id","5002");
        obj1.put("type","Hello");

        HashMap<String, String> obj2=new HashMap<String,String>();
        obj2.put("id","5003");
        obj2.put("type","Thulasi");

        List<Map<String,String>> jsonList=new ArrayList<Map<String,String>>();
        jsonList.add(obj1);

        System.out.println(obj1);

        jsonList.add(obj2);
        System.out.println(obj2);

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .header("Content-Type","application/json")
                .body(jsonList)
                .log().all()
        .when()
                .post("/post")
        .then()
                .extract().response().prettyPrint();
        }







}
