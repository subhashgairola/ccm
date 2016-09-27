package com.ccm.web.dao;

import java.util.List;

import com.ccm.excel.utils.ExcelRow;

public interface ExcelDao {

	void save(List<ExcelRow> rows, String sourceSystem);

}
