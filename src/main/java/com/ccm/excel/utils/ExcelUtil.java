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
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static Map<String, Map<Integer, String>> columnMapping = null;
	static {
		columnMapping = new HashMap<String, Map<Integer, String>>();
		Map<Integer, String> NAVMap = new HashMap<Integer, String>();
		NAVMap.put(0, Constants.COL_CLIENT_ID);
		NAVMap.put(1, Constants.COL_CLIENT_NAME);
		NAVMap.put(2, Constants.COL_MOBILE_NUM);
		columnMapping.put(Constants.NAV_SOURCE_TYPE, NAVMap);

		Map<Integer, String> APSISmap = new HashMap<Integer, String>();
		APSISmap.put(0, Constants.COL_CLIENT_ID);
		APSISmap.put(1, Constants.COL_CLIENT_NAME);
		APSISmap.put(2, Constants.COL_EMAIL);
		APSISmap.put(3, Constants.COL_CREATION_DATE);
		columnMapping.put(Constants.APSIS_SOURCE_TYPE, APSISmap);

		Map<Integer, String> ZendexMap = new HashMap<Integer, String>();
		ZendexMap.put(0, Constants.COL_CLIENT_ID);
		ZendexMap.put(1, Constants.COL_CLIENT_NAME);
		ZendexMap.put(2, Constants.COL_EMAIL);
		ZendexMap.put(3, Constants.COL_PHONE_NO);
		ZendexMap.put(4, Constants.COL_CREATION_DATE);
		ZendexMap.put(5, Constants.COL_LAST_LOGIN_DATE);
		columnMapping.put(Constants.ZENDESK_SOURCE_TYPE, ZendexMap);
		
		Map<Integer, String> magentoMap = new HashMap<Integer, String>();
		magentoMap.put(0, Constants.COL_CLIENT_ID);
		magentoMap.put(1, Constants.COL_CLIENT_NAME);
		magentoMap.put(2, Constants.COL_MOBILE_NUM);
		magentoMap.put(3, Constants.COL_PHONE_NO);
		magentoMap.put(4, Constants.COL_ZIP);
		magentoMap.put(5, Constants.COL_COUNTRY);
		magentoMap.put(6, Constants.COL_CREATION_DATE);
		
		columnMapping.put(Constants.MAGENTO_SOURCE_TYPE, magentoMap);
		
		Map<Integer, String> reederIDMap = new HashMap<Integer, String>();
		reederIDMap.put(0, Constants.COL_CLIENT_ID);
		reederIDMap.put(1, Constants.COL_CLIENT_NAME);
		reederIDMap.put(2, Constants.COL_GENDER);
		reederIDMap.put(3, Constants.COL_EMAIL);
		reederIDMap.put(4, Constants.COL_PASSWORD);
		reederIDMap.put(5, Constants.COL_MOBILE_NUM);
		reederIDMap.put(6, Constants.COL_BIRTH_DATE);
		reederIDMap.put(7, Constants.COL_IP_ADDRESS);
		reederIDMap.put(8, Constants.COL_LOCATION);
		reederIDMap.put(9, Constants.COL_CREATION_DATE);
		
		columnMapping.put(Constants.REEDERID_SOURCE_TYPE, reederIDMap);

		/*
		 * Map<Integer, String> navMap = new HashMap<Integer, String>();
		 * navMap.put(0, "clientId"); navMap.put(1, "clientName"); navMap.put(2,
		 * "mobileNum"); columnMapping.put(Constants.APSIS_SOURCE_TYPE, NAVMap);
		 * 
		 * Map<Integer, String> navMap = new HashMap<Integer, String>();
		 * navMap.put(0, "clientId"); navMap.put(1, "clientName"); navMap.put(2,
		 * "mobileNum"); columnMapping.put(Constants.APSIS_SOURCE_TYPE, NAVMap);
		 */
	}

	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				return DateUtil.getJavaDate(cell.getNumericCellValue());
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			}
		}
		return null;
	}

	public static List<ExcelRow> getExcelRows(InputStream inputStream,
			String sourceType) throws IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
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
				// nextCell.setCellType(Cell.CELL_TYPE_STRING);
				int columnIndex = nextCell.getColumnIndex();
				for (int i = 0; i < totalColumns; i++) {
					if (i == columnIndex) {
						try{
						PropertyUtils.setProperty(aBook,
								columnMapping.get(sourceType).get(columnIndex),
								getCellValue(nextCell));
						}catch(IllegalAccessException|NoSuchMethodException|InvocationTargetException e){
							e.printStackTrace();
						}
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
