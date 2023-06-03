package Bdd;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class Logging
{
    @Test
    void requestAndResponseLogging()
    {
        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header(header1)
                .header(header2)
                .log().all() //Request Logging
        .when()
                .post("/post")
        .then()
                .log().all()   //Response  Logging
                .extract().response().prettyPrint();
    }


    @Test
    void configLog()
    {
        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header(header1)
                .header(header2)
                .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                log().ifValidationFails()

        .when()
                .post("/post")
        .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test
    void blackList()
    {
        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header(header1)
                .header(header2)
                .config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key")))
                .log().all()//Request Logging
        .when()
                .post("/post")
        .then()
                .log().all()   //Response  Logging
                .extract().response().prettyPrint();
    }


    @Test
    void blackListAll()
    {
//        HashMap<String,String> head=new HashMap<String,String>();
//        head.put("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
//        head.put("Content-Type","application/json");


        Set<String> head=new HashSet<String>();
        head.add("x-api-key");
        head.add("Content-Type");

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .headers("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560","Content-Type","application/json")
                .config(config.logConfig(LogConfig.logConfig().blacklistHeaders(head)))
                .log().all()//Request Logging
        .when()
                .post("/post")
                .then()
        .log().all()   //Response  Logging
                .extract().response().prettyPrint();
    }
}
