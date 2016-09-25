package com.ccm.excel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A dirty simple program that reads an Excel file.
 * 
 * @author www.codejava.net
 *
 */
public class ExcelUtil {

	/*public static void main(String[] args) throws IOException {
		String excelFilePath = "E:\\Java code\\Book.xlsx";
		//List<FirstExcelRow> listBooks = readBooksFromExcelFile(excelFilePath);
		//System.out.println(listBooks);
	}*/

	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

	public static List<ExcelRow> getExcelRows(InputStream inputStream) throws IOException {
		List<ExcelRow> listBooks = new ArrayList<>();

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			FirstExcelRow aBook = new FirstExcelRow();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 1:
					aBook.setClientId((String) getCellValue(nextCell));
					break;
				case 2:
					aBook.setClientName((String) getCellValue(nextCell));
					break;
				case 3:
					aBook.setClientDetails((String) getCellValue(nextCell));
					break;
				}
			}
			listBooks.add(aBook);
		}
		workbook.close();
		inputStream.close();
		return listBooks;
	}
}
