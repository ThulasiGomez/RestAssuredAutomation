package Pojo;

import Pages.Category;
import Pages.PetsData;
import Pages.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Swagger
{

    @Test
    void run()
    {
        PetsData petsData=new PetsData();
        petsData.setId(01);
        petsData.setName("Panda");
        petsData.setStatus("available");

        Category category=new Category();
        category.setId(02);
        category.setName("Doggie");

        petsData.setCategory(category);

        List<String> photoUrl=new ArrayList<>();
        photoUrl.add("url1");
        photoUrl.add("url2");

        petsData.setPhotoUrls(photoUrl);


       Response response= given().
                baseUri("https://petstore.swagger.io")
                .header("Content-Type","application/json")
        .when()
                .body(petsData)
                .post("/v2/pet")
        .then()
                .extract().response();

      PetsData petsResponse= response.body().as(PetsData.class); //Deserialization

      System.out.println(petsResponse.getName());
        System.out.println(petsResponse.getId());




    }


}
