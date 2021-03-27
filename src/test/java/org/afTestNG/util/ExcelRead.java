package org.afTestNG.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	private static final Logger logger = LogManager.getLogger(ExcelRead.class);

	public String[][] getDatafromExcel(String fileName) {
		String[][] inputData = null;
		try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(fileName)));) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getLastCellNum();
			logger.debug("Row count in excel is " + rowCount);
			logger.debug("column count in excel is " + columnCount);
			inputData = new String[rowCount-1][columnCount];
			for (int i = 1; i < rowCount; i++) {
				logger.debug("Inside first loop");
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < columnCount; j++) {
					logger.debug("Inside second loop");
					String cellValue = row.getCell(j).getStringCellValue();
					inputData[i - 1][j] = cellValue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputData;
	}
}
