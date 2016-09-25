package com.ccm.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ccm.excel.utils.ExcelRow;
import com.ccm.web.dao.ExcelDao;

@Repository
public class ExcelDaoImpl implements ExcelDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(List<ExcelRow> rows) {
			String sql = "INSERT INTO first_excel "
					+ "(clientId, clientName, clientDetails) VALUES (?, ?, ?)";

			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ExcelRow row = rows.get(i);
					ps.setString(1, row.getClientId());
					ps.setString(2, row.getClientName());
					ps.setString(3, row.getClientDetails());
				}

				public int getBatchSize() {
					return rows.size();
				}
			});

		}
}
