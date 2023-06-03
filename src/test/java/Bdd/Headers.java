package Bdd;

import io.restassured.http.Header;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Headers
{

    @Test
    void multiHeader()
    {
        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header(header1)
                .header(header2)
       .when()
                .post("/post")
       .then()
                .extract().response().prettyPrint();
    }


    @Test
    void usingMap()
    {
        HashMap<String,String> head=new HashMap<String,String>();
        head.put("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        head.put("Content-Type","application/json");

        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .headers(head)
        .when()
                .post("/post")
        .then()
                .extract().response().prettyPrint();
    }


    @Test
    void multipleValueUsingHeaders()
    {

        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");
        io.restassured.http.Headers hs=new io.restassured.http.Headers(header1,header2);
        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .headers(hs)
        .when()
                .post("/post")
        .then()
                .log().all()
                .extract().response().prettyPrint();
    }

    @Test
    void assertResponseHeader()
    {
        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");
        io.restassured.http.Headers hs=new io.restassured.http.Headers(header1,header2);
        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .headers(hs)
        .when()
                .post("/post")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .headers("X-RateLimit-Limit","120","Vary","Accept-Encoding");
    }


    @Test
    void extractResponseHeader()
    {
        Header header1=new Header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560");
        Header header2=new Header("Content-Type","application/json");
        io.restassured.http.Headers hs=new io.restassured.http.Headers(header1,header2);
      io.restassured.http.Headers head= given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .headers(hs)
        .when()
                .post("/post")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().headers();


      for(Header var:head)
      {
        System.out.println("Header Name: "+var.getName());
        System.out.println("Header Name: "+var.getValue());
      }
    }
}
