package DataDr;

import DataDrivenPges.CreateUser;
import DataDrivenPges.XLUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

public class UsersGoRest
{

    @Test(dataProvider = "userData" )
    void run(String uname,String uemail,String ugender,String ustatus)
    {
        CreateUser createUser=new CreateUser();
        createUser.postUser(uname,uemail,ugender,ustatus);
    }

    @DataProvider(name="userData")
    String[][] userData() throws IOException {
        String path = "/Users/thulasiram.a.vc/Documents/Res/src/test/resources/DataSet.xlsx";

        int rows = XLUtils.getRowCount(path, "Sheet1");
        int col = XLUtils.getCellCount(path, "Sheet1", 1);

        String empData[][] = new String[rows][col];
        for (int i=1;i<=rows;i++)
        {
            for(int j=0;j<col;j++)
            {
                empData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }

        return empData;
    }
}
