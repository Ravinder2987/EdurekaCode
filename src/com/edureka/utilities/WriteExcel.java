package com.edureka.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class WriteExcel {
	WebDriver driver;

	public void writeData() throws IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\Vishal\\eclipse-workspace\\Edureka\\loginCredentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		sheet.getRow(1).createCell(2).setCellValue("Passed");

		FileOutputStream fos = new FileOutputStream ("C:\\Users\\Vishal\\eclipse-workspace\\Edureka\\loginCredentials.xlsx");
		workbook.write(fos);
		workbook.close();


	}
}

