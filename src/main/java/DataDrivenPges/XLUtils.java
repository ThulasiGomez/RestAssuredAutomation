package DataDrivenPges;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils
{
    public static FileInputStream fileInputStream;
    public static FileOutputStream fileOutputStream;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int  getRowCount(String xlFile,String xlsheet) throws IOException {
        fileInputStream=new FileInputStream(xlFile);
        wb=new XSSFWorkbook(fileInputStream);
        ws=wb.getSheet(xlsheet);
        int rowCount=ws.getLastRowNum();
        wb.close();
        fileInputStream.close();
        return rowCount;
    }
    public static int  getCellCount(String xlFile,String xlsheet,int rowNum) throws IOException {
        fileInputStream=new FileInputStream(xlFile);
        wb=new XSSFWorkbook(fileInputStream);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rowNum);
        int cellCount=row.getLastCellNum();
        wb.close();
        fileInputStream.close();
        return cellCount;
    }


    public static String getCellData(String xlFile, String xlsheet, int rowNum, int columnNum) throws IOException {
        fileInputStream=new FileInputStream(xlFile);
        wb=new XSSFWorkbook(fileInputStream);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rowNum);
        cell=row.getCell(columnNum);
        String data;
        try
        {
            DataFormatter d=new DataFormatter();
            String cellData=d.formatCellValue(cell);
            return cellData;
        }
        catch (Exception e)
        {
            data ="";
        }
        wb.close();
        fileInputStream.close();
        return data;
    }
}
