package Bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

public class Jackson
{
    @BeforeMethod
    public void beforeMethod()
    {
        RequestSpecBuilder rs=new RequestSpecBuilder().

                log(LogDetail.ALL);

        RestAssured.requestSpecification= rs.build();

        ResponseSpecBuilder rb=new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification= rb.build();

    }


    @Test
    void validate_post_request_payload_as_map() throws JsonProcessingException
    {
        HashMap<String,Object> mainObj=new HashMap<>();

        HashMap<String,String> nestedObj=new HashMap<>();
        nestedObj.put("name","panda");
        nestedObj.put("type","personal");
        nestedObj.put("description","Rest Assured Created This");

        mainObj.put("workspace",nestedObj);
        ObjectMapper mapper=new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String map=mapper.writeValueAsString(mainObj);

        System.out.println(map);

        given().
                baseUri("https://api.postman.com").
                header("x-api-Key","PMAK-61d050d5fc12811c4e30ddb4-f4b51d8424d0ec278e92f5e118c3c9c2dd").
        when().
                body(map).
                post("/workspaces").
        then().
                log().all().
                assertThat()
                .body("workspace.name", Matchers.equalTo("panda"),
                        "workspace.id",matchesPattern("^[a-z0-9-]{36}$"));

    }
}
