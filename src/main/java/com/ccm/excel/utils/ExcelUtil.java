package com.ccm.excel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static Map<String, Map<Integer, String>> columnMapping = null;
	static {
		columnMapping = new HashMap<String, Map<Integer, String>>();
		Map<Integer, String> NAVMap = new HashMap<Integer, String>();
		NAVMap.put(0, "clientId");
		NAVMap.put(1, "clientName");
		NAVMap.put(2, "mobileNum");
		columnMapping.put(Constants.NAV_SOURCE_TYPE, NAVMap);
		
		Map<Integer, String> APSISmap = new HashMap<Integer, String>();
		APSISmap.put(0, "clientId");
		APSISmap.put(1, "clientName");
		APSISmap.put(2, "email");
		APSISmap.put(3, "creationDate");
		columnMapping.put(Constants.APSIS_SOURCE_TYPE, NAVMap);
		
		/*Map<Integer, String> MagentoMap = new HashMap<Integer, String>();
		MagentoMap.put(0, "clientId");
		MagentoMap.put(1, "clientName");
		MagentoMap.put(2, "mobileNum");
		columnMapping.put(Constants.APSIS_SOURCE_TYPE, NAVMap);
		
		Map<Integer, String> navMap = new HashMap<Integer, String>();
		navMap.put(0, "clientId");
		navMap.put(1, "clientName");
		navMap.put(2, "mobileNum");
		columnMapping.put(Constants.APSIS_SOURCE_TYPE, NAVMap);
		
		Map<Integer, String> navMap = new HashMap<Integer, String>();
		navMap.put(0, "clientId");
		navMap.put(1, "clientName");
		navMap.put(2, "mobileNum");
		columnMapping.put(Constants.APSIS_SOURCE_TYPE, NAVMap);*/
	}

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

	public static List<ExcelRow> getExcelRows(InputStream inputStream,
			String sourceType) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<ExcelRow> listBooks = new ArrayList<>();

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		int totalColumns = firstSheet.getRow(0).getPhysicalNumberOfCells();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			ExcelRowImpl aBook = new ExcelRowImpl();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				nextCell.setCellType(Cell.CELL_TYPE_STRING);
				int columnIndex = nextCell.getColumnIndex();
				for (int i = 0; i < totalColumns; i++) {
					if (i == columnIndex) {
						PropertyUtils.setProperty(aBook,
								columnMapping.get(sourceType).get(columnIndex),
								 getCellValue(nextCell));
						break;
					}
				}
			}
			listBooks.add(aBook);
		}
		workbook.close();
		inputStream.close();
		return listBooks;
	}
}
