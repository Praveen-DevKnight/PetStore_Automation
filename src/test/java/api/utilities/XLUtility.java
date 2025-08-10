package api.utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {
    private Workbook workbook;
    private String filePath;

    public XLUtility(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    // Get row count of a sheet (number of physically used rows)
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        return sheet.getPhysicalNumberOfRows();
    }

    // Get cell count (number of physically used cells) in a specific row
    public int getCellCount(String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        Row row = sheet.getRow(rowNum);
        if (row == null) return 0;
        return row.getPhysicalNumberOfCells();
    }

    // Get cell data from sheet, rowNum, colNum as String
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Close workbook resource
    public void close() throws IOException {
        workbook.close();
    }

    // For testing
    public static void main(String[] args) throws IOException {
        XLUtility xl = new XLUtility("testdata.xlsx");
        System.out.println("Row count: " + xl.getRowCount("Sheet1"));
        System.out.println("Cell count (row 0): " + xl.getCellCount("Sheet1", 0));
        System.out.println("Cell data (0,0): " + xl.getCellData("Sheet1", 0, 0));
        xl.close();
    }
}
