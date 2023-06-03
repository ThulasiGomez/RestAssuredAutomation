package DataDrivenPges;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class CreateUser
{
   public void postUser(String uname,String uemail,String ugender,String ustatus)
    {
        RestAssured.baseURI="https://gorest.co.in/";


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name",uname);
        jsonObject.put("email",uemail);
        jsonObject.put("gender",ugender);
        jsonObject.put("status",ustatus);

        Response response=
                (Response) given()
                        .header("Content-Type","application/json")
                        .auth().oauth2("51e45659b17436f2804dee7db56facf715d7843757726c610225da60694f3162").
                when()
                        .body(jsonObject.toJSONString()).
                        post("/public/v2/users").
                then()
                        .log().all().extract().response();
        String responseBody=response.getBody().toString();


        Assert.assertEquals(response.statusCode(),201);
    }
}
