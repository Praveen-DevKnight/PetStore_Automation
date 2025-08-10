package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException {
	    String path = System.getProperty("user.dir") + "//TestData//UserData.xlsx";
	    XLUtility xl = new XLUtility(path);

	    int totalRows = xl.getRowCount("Sheet1");
	    int totalCols = xl.getCellCount("Sheet1", 1);

	    // Temporary array (max size)
	    String[][] tempData = new String[totalRows][totalCols];
	    int validRowCount = 0;

	    for (int i = 1; i <= totalRows; i++) {
	        String firstCell = xl.getCellData("Sheet1", i, 0);

	        if (firstCell == null || firstCell.trim().isEmpty()) {
	            // Skip empty rows
	            continue;
	        }

	        // Copy data from this row
	        for (int j = 0; j < totalCols; j++) {
	            tempData[validRowCount][j] = xl.getCellData("Sheet1", i, j);
	        }
	        validRowCount++;
	    }

	    // Now create final array with only valid rows
	    String[][] finalData = new String[validRowCount][totalCols];
	    for (int i = 0; i < validRowCount; i++) {
	        System.arraycopy(tempData[i], 0, finalData[i], 0, totalCols);
	    }

	    xl.close();
	    return finalData;
	}

	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
	    String path = System.getProperty("user.dir") + "//TestData//UserData.xlsx";
	    XLUtility xl = new XLUtility(path);

	    int totalRows = xl.getRowCount("Sheet1");
	    // We assume username is in column 1

	    String[] tempData = new String[totalRows];
	    int validCount = 0;

	    for (int i = 1; i <= totalRows; i++) {
	        String firstCell = xl.getCellData("Sheet1", i, 0);
	        if (firstCell == null || firstCell.trim().isEmpty()) {
	            continue; // skip empty row
	        }
	        tempData[validCount] = xl.getCellData("Sheet1", i, 1);
	        validCount++;
	    }

	    String[] finalData = new String[validCount];
	    System.arraycopy(tempData, 0, finalData, 0, validCount);

	    xl.close();
	    return finalData;
	}

	

	
	
	
	
}
