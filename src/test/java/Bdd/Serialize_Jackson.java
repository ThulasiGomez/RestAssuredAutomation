package Bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Serialize_Jackson
{

    // Jackson – A java-based library to serialize or map java objects to JSON and vice versa.


   // java obj   ->  Json   Serialization
    //Json    ->  Java Obj De Serialization



    @Test
    void serial() throws JsonProcessingException {
        HashMap<String,Object> mainObj=new HashMap<>();

        HashMap<String,String> nestedObj=new HashMap<>();
        nestedObj.put("name","panda");
        nestedObj.put("type","personal");
        nestedObj.put("description","Rest Assured Created This");
        mainObj.put("workspace",nestedObj);

        ObjectMapper obj=new ObjectMapper();
        obj.enable(SerializationFeature.INDENT_OUTPUT);

        String map=obj.writeValueAsString(mainObj);

        given().
                baseUri("https://api.postman.com/")
                .header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .header("Content-Type","application/json")
                .body(map)
                .log().all()
        .when()
                .post("/workspaces")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("workspace.name",equalTo("panda"));
    }



    @Test
    void payload_as_Json_ArrayList() throws JsonProcessingException {
        HashMap<String, String> obj1=new HashMap<String,String>();
        obj1.put("id","5002");
        obj1.put("type","Hello");

        HashMap<String, String> obj2=new HashMap<String,String>();
        obj2.put("id","5003");
        obj2.put("type","Thulasi");

        List<Map<String,String>> jsonList=new ArrayList<Map<String,String>>();
        jsonList.add(obj1);
        jsonList.add(obj2);

        ObjectMapper objectMapper=new ObjectMapper();
        String jsonStr=objectMapper.writeValueAsString(jsonList);


        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .header("Content-Type","application/json")
                .body(jsonStr)
                .log().all()
        .when()
                .post("/post")
        .then()
                .extract().response().prettyPrint();
    }


    @Test
    void jackson_ArrayNode_to_Json_Array() throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        ArrayNode arrayNodeList=objectMapper.createArrayNode();
        ObjectNode obj1=objectMapper.createObjectNode();
        obj1.put("id","5002");
        obj1.put("type","Hello");
        ObjectNode obj2=objectMapper.createObjectNode();
        obj2.put("id","5003");
        obj2.put("type","test");
        arrayNodeList.add(obj1);
        arrayNodeList.add(obj2);
        String jsonStr=objectMapper.writeValueAsString(arrayNodeList);


        given().
                baseUri("https://f9e5d436-7183-4aae-b1c9-d9a12c56a615.mock.pstmn.io/")
                .header("x-api-key","PMAK-644f99361ff62a0610d816fb-04461cefb9ab4a96c021941c9479aff560")
                .header("Content-Type","application/json")
                .body(jsonStr)
                .log().all()
        .when()
                .post("/post")
        .then()
                .extract().response().prettyPrint();
    }
}
