package utilities;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;




import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import stepDefinitions.TestBase;

public class Genericfun extends TestBase {
	
	static XSSFWorkbook workbook;
	
	public static String getExcelData(String sheetname, int rowNum, int cellNum) throws Exception {

		XSSFSheet sheet =openExcelFile(sheetname);
	 		// get the control of the row
	 		XSSFRow row = sheet.getRow(rowNum);

	 		// Get the data from desired cell of the current row
	 		String data = row.getCell(cellNum).getStringCellValue();

	 		System.out.println("Data from Test data sheet is = " + data);

			return data;

	}
	public static String searchValueInExcel(String sheetname, int rowNum, int cellNum) throws Exception {
	 		XSSFSheet sheet =openExcelFile(sheetname);

	 		// get the control of the row
	 
	for (int rowIndex = 0; rowIndex < sheet.getLastRowNum(); rowIndex++) {
        XSSFRow row = sheet.getRow(rowIndex);
        if (row != null && row.getCell(0).getStringCellValue().equals("Field")) {
            return row.getCell(1).getRawValue();
        }
}
	return"";
	}
public static XSSFSheet openExcelFile(String sheetname) throws IOException
{
    
	try {
		//Create an object of File class to open xlsx file

		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData"+"\\"+"TestData.xlsx");

		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);
		
		// Open Work book in a read mode.
		workbook = new XSSFWorkbook(inputStream);

 // Get the control of sheet
	    return workbook.getSheet(sheetname);
			
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw e;
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw e;
	}

    }

}
