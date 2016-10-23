package com.ccm.excel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static Map<String, Map<Integer, String>> columnMapping = null;
	static Map<String, String[]> headerMapping = null;
	static {
		columnMapping = new LinkedHashMap<String, Map<Integer, String>>();
		Map<Integer, String> APSISmap = new HashMap<Integer, String>();
		APSISmap.put(0, Constants.SOURCE);
		APSISmap.put(1, Constants.COL_ID);
		APSISmap.put(2, Constants.COL_NAME);
		APSISmap.put(3, Constants.COL_EMAIL);
		APSISmap.put(4, Constants.COL_CREATION_DATE);
		columnMapping.put(Constants.APSIS_SOURCE_TYPE, APSISmap);

		Map<Integer, String> NAVMap = new HashMap<Integer, String>();
		NAVMap.put(0, Constants.SOURCE);
		NAVMap.put(1, Constants.COL_ID);
		NAVMap.put(2, Constants.COL_NAME);
		NAVMap.put(3, Constants.COL_PHONE_NO);
		NAVMap.put(4, Constants.COL_LOCATION);
		columnMapping.put(Constants.NAV_SOURCE_TYPE, NAVMap);

		Map<Integer, String> ZendexMap = new HashMap<Integer, String>();
		ZendexMap.put(0, Constants.SOURCE);
		ZendexMap.put(1, Constants.COL_ID);
		ZendexMap.put(2, Constants.COL_NAME);
		ZendexMap.put(3, Constants.COL_EMAIL);
		ZendexMap.put(4, Constants.COL_CREATION_DATE);
		ZendexMap.put(5, Constants.COL_LAST_LOGIN_DATE);
		columnMapping.put(Constants.ZENDESK_SOURCE_TYPE, ZendexMap);

		Map<Integer, String> magentoMap = new HashMap<Integer, String>();
		magentoMap.put(0, Constants.SOURCE);
		magentoMap.put(1, Constants.COL_ID);
		magentoMap.put(2, Constants.COL_NAME);
		magentoMap.put(3, Constants.COL_EMAIL);
		magentoMap.put(4, Constants.COL_PHONE_NO);
		magentoMap.put(5, Constants.COL_CREATION_DATE);
		magentoMap.put(6, Constants.COL_ZIP);
		magentoMap.put(7, Constants.COL_COUNTRY);
		magentoMap.put(8, Constants.COL_STATE_NAME);

		columnMapping.put(Constants.MAGENTO_SOURCE_TYPE, magentoMap);

		Map<Integer, String> reederIDMap = new HashMap<Integer, String>();
		reederIDMap.put(0, Constants.SOURCE);
		reederIDMap.put(1, Constants.COL_ID);
		reederIDMap.put(2, Constants.COL_NAME);
		reederIDMap.put(3, Constants.COL_EMAIL);
		reederIDMap.put(4, Constants.COL_PHONE_NO);
		reederIDMap.put(5, Constants.COL_CREATION_DATE);
		reederIDMap.put(6, Constants.COL_PASSWORD);
		reederIDMap.put(7, Constants.COL_GENDER);
		reederIDMap.put(8, Constants.COL_IS_NEWS);
		reederIDMap.put(9, Constants.COL_LOCATION);
		reederIDMap.put(10, Constants.COL_IP_ADDRESS);
		reederIDMap.put(11, Constants.COL_BIRTH_DATE);
		columnMapping.put(Constants.REEDERID_SOURCE_TYPE, reederIDMap);

		headerMapping = new LinkedHashMap<String, String[]>();

		headerMapping.put(Constants.NAV_SOURCE_TYPE, Constants.NAV_COLUMNS);
		headerMapping.put(Constants.APSIS_SOURCE_TYPE, Constants.APSIS_COLUMNS);
		headerMapping.put(Constants.ZENDESK_SOURCE_TYPE, Constants.ZENDESK_COLUMNS);
		headerMapping.put(Constants.MAGENTO_SOURCE_TYPE, Constants.MAGENTO_COLUMNS);
		headerMapping.put(Constants.REEDERID_SOURCE_TYPE, Constants.REEDER_COLUMNS);

	}

	public static List<ExcelRow> getExcelRows(InputStream inputStream, String sourceType) throws IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InvalidExcelException {
		List<ExcelRow> listBooks = new ArrayList<>();

		Workbook workbook = new XSSFWorkbook(inputStream);
		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		int totalColumns = firstSheet.getRow(0).getPhysicalNumberOfCells();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if (nextRow.getRowNum() == 0) {
				validateExcel(nextRow, sourceType);
			}
			if (nextRow.getRowNum() != 0) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				ExcelRowImpl aBook = new ExcelRowImpl();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					for (int i = 0; i < totalColumns; i++) {
						if (i == columnIndex) {
							objFormulaEvaluator.evaluate(nextCell);
							String cellValueStr = objDefaultFormat.formatCellValue(nextCell, objFormulaEvaluator);
							PropertyUtils.setProperty(aBook, columnMapping.get(sourceType).get(columnIndex), cellValueStr);
							break;
						}
					}
				}
				listBooks.add(aBook);
			}
		}
		workbook.close();
		inputStream.close();
		return listBooks;
	}

	private static void validateExcel(Row nextRow, String sourceType) throws InvalidExcelException {
		String headerColumns[] = headerMapping.get(sourceType);
		String message = "Error uploading file due to invalid format. The correct format for " + sourceType
					+ " source is: " + String.join(", ", headerColumns);
		if(sourceType.equals(Constants.REEDERID_SOURCE_TYPE)){
			message  = message + " [DD-MM-YYYY]";
		}
		if (nextRow.getPhysicalNumberOfCells() != headerColumns.length) {
			throw new InvalidExcelException(message);
		}
		for (int i = 0; i < headerColumns.length; i++) {
			String column = nextRow.getCell(i).getStringCellValue().trim();
			if (!column.equalsIgnoreCase(headerColumns[i])) {
				throw new InvalidExcelException(message);
			}
		}
	}
}
