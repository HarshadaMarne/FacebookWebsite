package Utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class TestDataReader {
    static HashMap<String,HashMap<String, String>> data;

    public static void main(String args[])
    {
        init();
    }
    public static void init(){
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream("src/test/resources/Data.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet=workbook.getSheet("Sheet1");

        data=new HashMap<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row=sheet.getRow(i);
            if(row != null && row.getCell(0)!=null) {
//                System.out.println(i);

                String key = row.getCell(0).getStringCellValue();
                HashMap<String, String> rowData = new HashMap<>();

                for (int j = 1; j < row.getLastCellNum(); j++) {
//                    System.out.println(j);
                    String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
                    rowData.put(columnName, row.getCell(j).getStringCellValue());

                }

                data.put(key, rowData);
            }
        }

        //Hashmap -> key, value
        //Tc_name -> List<String> -> [test user, india, my learning is superb so far]
        //Tc_name -> HashMap<key,value> [(TypeValue, Test User),(Country,India),(Status,My learning is superb so far.)
    }

    public static HashMap<String,String> getData(String key)
    {
        init();
        return data.get(key);
    }
}
