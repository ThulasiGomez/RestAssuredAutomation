package Bdd;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class DownloadAFile
{
    @Test
    void download() throws IOException {
      byte[] by=  given().
                baseUri("https://github.com")
        .when()
                .get("/bitbar/test-samples/blob/master/apps/android/bitbar-sample-app.apk")
        .then()
                .log().all()
                .extract().response()
                .asByteArray();

        OutputStream os=new FileOutputStream(new File("demo.apk"));
        os.write(by);
        os.close();
    }
}
