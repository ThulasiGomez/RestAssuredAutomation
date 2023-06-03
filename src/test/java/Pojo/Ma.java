package Pojo;

import Pages.Category;
import Pages.PetsData;
import Pages.Tag;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Ma
{
    @Test
    void run()
    {
        PetsData petsData=new PetsData();
        petsData.setId(01);
        petsData.setName("Panda");
        petsData.setStatus("available");

        Category category=new Category();
        category.setId(002);
        petsData.setCategory(category);

        Tag tag1=new Tag();
        tag1.setId(1000L);
        tag1.setName("Tag1");



        List<Tag>  tags=new ArrayList<>();
        tags.add(tag1);


        petsData.setTags(tags);

        List<String> photo=new ArrayList<>();
        photo.add("Url1");
        photo.add("Url1");

        petsData.setPhotoUrls(photo);

        given().
                baseUri("https://petstore.swagger.io")
                .header("Content-Type","application/json")
        .when()
                .body(petsData)
                .post("/v2/pet")
        .then()
                .extract().response().prettyPrint();


    }
}
