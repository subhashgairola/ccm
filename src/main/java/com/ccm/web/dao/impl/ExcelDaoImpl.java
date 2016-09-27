package com.ccm.web.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
	public void save(List<ExcelRow> rows, String sourceSystem) {
		String sql = "INSERT INTO customerdetail "
				+ "(clientId, clientName, password, email, mobileNum, birthDate, gender, creationDate, ipAddress, country, city, insertedDate, updateDate, updatedBy, sourceSystem)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ExcelRow row = rows.get(i);
				ps.setString(1, row.getClientId());
				ps.setString(2, row.getClientName());
				ps.setString(3, row.getPassword());
				ps.setString(4, row.getEmail());
				ps.setString(5, row.getMobileNum());
				if (row.getBirthDate() == null) {
					ps.setNull(6, java.sql.Types.DATE);
				} else {
					ps.setDate(6, getDate(row.getBirthDate()));
				}
				ps.setString(7, row.getGender());
				if (row.getCreationDate() == null) {
					ps.setNull(8, java.sql.Types.TIMESTAMP);
				} else {
					ps.setTimestamp(8, new java.sql.Timestamp(row.getCreationDate().getTime()));
				}
				ps.setString(9, row.getIpAddress());
				ps.setString(10, row.getCountry());
				ps.setString(11, row.getCity());
				Timestamp ts = getTimestamp(null);
				ps.setTimestamp(12, ts);
				ps.setTimestamp(13, ts);
				//remove this
				ps.setInt(14, 1);
				ps.setString(15, row.getSourceSystem());
			}

			public int getBatchSize() {
				return rows.size();
			}
		});

	}

	private static Date getDate(String dateString) {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		java.util.Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	private static Timestamp getTimestamp(String timestamp) {
		java.util.Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		if (timestamp == null) {
			date = new java.util.Date();
		} else {
			try {
				date = dateFormat.parse(timestamp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return new java.sql.Timestamp(date.getTime());
	}
}
