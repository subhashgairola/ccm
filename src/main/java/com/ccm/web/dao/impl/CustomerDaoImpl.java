package com.ccm.web.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;
import com.ccm.web.dao.CustomerDao;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CustomerDetail> getCustomerDetails() {
		return jdbcTemplate.query("select * from customerdetail",
				new ResultSetExtractor<List<CustomerDetail>>() {
					@Override
					public List<CustomerDetail> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<CustomerDetail> cusDetails = new ArrayList<CustomerDetail>();
						CustomerDetail custDetail = null;
						while (rs.next()) {
							custDetail = new CustomerDetail();
							custDetail.setCustomerDetailId(rs.getInt(1));
							custDetail.setClientId(rs.getString(2));
							custDetail.setClientName(rs.getString(3));
							custDetail.setPassword(rs.getString(4));
							custDetail.setEmail(rs.getString(5));
							custDetail.setMobileNum(rs.getString(6));
							custDetail.setBirthDate(rs.getDate(7)!= null ? rs.getDate(7).toString():null);
							custDetail.setGender(rs.getString(8));
							custDetail.setCreationDate(rs.getDate(9));
							custDetail.setCountry(rs.getString(10));
							custDetail.setCity(rs.getString(11));
							custDetail.setInsertedDate(rs.getTimestamp(12));
							custDetail.setInsertedDate(rs.getTimestamp(13));
							custDetail.setUpdateDate(rs.getTimestamp(14));
							custDetail.setUpdatedBy(rs.getInt(15));
							custDetail.setSourceSystem(rs.getString(16));
							custDetail.setZip(rs.getString(17));
							custDetail.setPhoneNum(rs.getString(18));
							custDetail.setLocation(rs.getString(19));
							custDetail.setLastLogin(rs.getDate(20));
							cusDetails.add(custDetail);
						}
						return cusDetails;
					}
				});
	}

	@Override
	public void save(List<ExcelRow> rows, String sourceSystem) {
		String sql = "INSERT INTO customerdetail "
				+ "(clientId, clientName, password, email, mobileNum, birthDate, gender, creationDate, ipAddress, country, city, insertedDate,"
				+ " updateDate, updatedBy, sourceSystem, zip, phoneNum, location, lastLogin)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
					ps.setTimestamp(8, new java.sql.Timestamp(row
							.getCreationDate().getTime()));
				}
				ps.setString(9, row.getIpAddress());
				ps.setString(10, row.getCountry());
				ps.setString(11, row.getCity());
				Timestamp ts = getTimestamp(null);
				ps.setTimestamp(12, ts);
				ps.setTimestamp(13, ts);
				// remove this
				ps.setInt(14, 1);
				ps.setString(15, sourceSystem);
				ps.setString(16, row.getZip());
				ps.setString(17, row.getPhoneNum());
				ps.setString(18, row.getLocation());
				if (row.getBirthDate() == null) {
					ps.setNull(19, java.sql.Types.TIMESTAMP);
				} else {
					ps.setTimestamp(19, new java.sql.Timestamp(row.getLastLogin().getTime()));
				}
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