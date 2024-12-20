package Utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {
    XSSFSheet sheet;
    int totalrows;
    int totalcells;

    public void filereading() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\desus\\IdeaProjects\\HybridAutomation\\src\\test\\resources\\EXCEL_DATA_READING_AUTOMATION.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
     sheet = workbook.getSheet("Sheet1");
    }
       public int getrowcount() {
             totalrows = sheet.getLastRowNum();
             return totalrows;
        }
        public int cellcount() {
             totalcells = sheet.getRow(1).getLastCellNum();
             return totalcells;
        }

    public String[][] getCellData(int totalrows, int totalcells) {
        // Create a 2D array to store data from columns 1 and 2
        String[][] data = new String[totalrows][2]; // 2 columns to store data

        for (int r = 1; r <= totalrows; r++) {
            XSSFRow row = sheet.getRow(r);

            // Store values from columns 1 and 2
            data[r-1][0] = row.getCell(0).toString(); // Column 1
            data[r-1][1] = row.getCell(1).toString(); // Column 2
        }

        return data;
    }






}
