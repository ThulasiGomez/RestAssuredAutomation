package Bdd;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestSpecification
{
    io.restassured.specification.RequestSpecification req;
    @BeforeTest
    void requestSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .addHeader("x-api-key", "PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .addHeader("Content-Type","application/json")
                .log(LogDetail.ALL);

        req=  requestSpecBuilder.build();

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
        jsonList.add(obj2);
        given(req)
                .body(jsonList)
        .when()
                .post("/post")
        .then()
                .extract().response().prettyPrint();
    }
}
