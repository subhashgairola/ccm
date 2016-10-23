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

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ccm.excel.utils.Constants;
import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;
import com.ccm.web.dao.CustomerDao;
import com.ccm.web.dao.StateDao;
import com.ccm.web.entity.State;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Resource
	private StateDao stateDao;

	private static final Log LOGGER = LogFactory.getLog(CustomerDaoImpl.class);

	@Override
	public long getTotalRecords() throws DataAccessException {
		String sql = "select count(customerDetailId) from customerdetail";
		long count = jdbcTemplate.queryForObject(sql, new Object[] {}, Long.class);
		return count;

	}

	public long getCountBySearch(String searchStr) throws DataAccessException {
		String sql = "select count(customerDetailId) from customerdetail where name LIKE ? OR email LIKE ? OR phoneNum LIKE ? OR source LIKE ?";
		long count = jdbcTemplate.queryForObject(sql, new Object[] { "%" + searchStr + "%", "%" + searchStr + "%", "%" + searchStr + "%",
				"%" + searchStr + "%" }, Long.class);
		return count;

	}

	@Override
	public List<CustomerDetail> getCustomerDetails(int offset, int limit, String orderByCol, String sortOrder) throws DataAccessException {
		return jdbcTemplate.query(
				"SELECT customerdetail.*,states.stateName FROM customerdetail LEFT JOIN states  ON customerdetail.stateId=states.stateId ORDER BY "
						+ orderByCol + " " + sortOrder + " LIMIT " + limit + " OFFSET " + offset, new ResultSetExtractor<List<CustomerDetail>>() {
					@Override
					public List<CustomerDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CustomerDetail> cusDetails = new ArrayList<CustomerDetail>();
						CustomerDetail custDetail = null;
						while (rs.next()) {
							custDetail = new CustomerDetail();
							custDetail.setCustomerDetailId(rs.getInt(1));
							custDetail.setId(rs.getString(2));
							custDetail.setName(rs.getString(3));
							custDetail.setPassword(rs.getString(4));
							custDetail.setEmail(rs.getString(5));
							custDetail.setMobileNum(rs.getString(6));
							custDetail.setBirthDate(rs.getDate(7) != null ? convertDateString(rs.getDate(7).toString()) : null);
							custDetail.setGender(rs.getString(8));
							custDetail.setCreationDate(rs.getString(9));
							custDetail.setIpAddress(rs.getString(10));
							custDetail.setCountry(rs.getString(11));
							custDetail.setCity(rs.getString(12));
							custDetail.setInsertedDate(rs.getTimestamp(13));
							custDetail.setUpdateDate(rs.getTimestamp(14));
							custDetail.setUpdatedBy(rs.getInt(15));
							custDetail.setSource(rs.getString(16));
							custDetail.setZip(rs.getString(17));
							custDetail.setPhoneNum(rs.getString(18));
							custDetail.setLocation(rs.getString(19));
							custDetail.setLastLogin(rs.getString(20));
							custDetail.setStateId(rs.getInt(21));
							custDetail.setStateName(rs.getString(22));
							cusDetails.add(custDetail);
						}
						return cusDetails;
					}
				});
	}

	@Override
	public List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset, int limit, String searchStr, String orderByCol, String sortOrder)
			throws DataAccessException {

		return jdbcTemplate.query(
				"SELECT customerdetail.*,states.stateName FROM customerdetail LEFT JOIN states  ON customerdetail.stateId=states.stateId WHERE name LIKE '%"
						+ searchStr + "%' OR" + " email LIKE '%" + searchStr + "%' OR phoneNum LIKE '%" + searchStr + "%' OR source  LIKE '%"
						+ searchStr + "%' ORDER BY " + orderByCol + " " + sortOrder + "  LIMIT " + limit + " OFFSET " + offset,
				new ResultSetExtractor<List<CustomerDetail>>() {
					@Override
					public List<CustomerDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CustomerDetail> cusDetails = new ArrayList<CustomerDetail>();
						CustomerDetail custDetail = null;
						while (rs.next()) {
							custDetail = new CustomerDetail();
							custDetail.setCustomerDetailId(rs.getInt(1));
							custDetail.setId(rs.getString(2));
							custDetail.setName(rs.getString(3));
							custDetail.setPassword(rs.getString(4));
							custDetail.setEmail(rs.getString(5));
							custDetail.setMobileNum(rs.getString(6));
							custDetail.setBirthDate(rs.getDate(7) != null ? convertDateString(rs.getDate(7).toString()) : null);
							custDetail.setGender(rs.getString(8));
							custDetail.setCreationDate(rs.getString(9));
							custDetail.setIpAddress(rs.getString(10));
							custDetail.setCountry(rs.getString(11));
							custDetail.setCity(rs.getString(12));
							custDetail.setInsertedDate(rs.getTimestamp(13));
							custDetail.setUpdateDate(rs.getTimestamp(14));
							custDetail.setUpdatedBy(rs.getInt(15));
							custDetail.setSource(rs.getString(16));
							custDetail.setZip(rs.getString(17));
							custDetail.setPhoneNum(rs.getString(18));
							custDetail.setLocation(rs.getString(19));
							custDetail.setLastLogin(rs.getString(20));
							custDetail.setStateId(rs.getInt(21));
							custDetail.setStateName(rs.getString(22));
							cusDetails.add(custDetail);
						}
						return cusDetails;
					}
				});

	}

	@Override
	public List<ExcelRow> saveRows(List<ExcelRow> rows, String sourceSystem) throws DataAccessException {
		LOGGER.info("********************** saveRows() start: Total  " + rows.size() + " rows to insert.");
		List<ExcelRow> errorRows = new ArrayList<ExcelRow>();
		String sql = "INSERT INTO customerdetail "
				+ "(id, name, password, email, mobileNum, birthDate, gender, creationDate, ipAddress, country, city, insertedDate,"
				+ " updateDate, updatedBy, source, zip, phoneNum, location, lastLogin, stateId)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		for (ExcelRow row : rows) {
			if (sourceSystem.equalsIgnoreCase(Constants.NAV_SOURCE_TYPE) && row.getPhoneNum() != null && isCustomerDuplicate("phone", row.getPhoneNum())) {
				errorRows.add(row);
				LOGGER.info("Duplicate row: A user with this PhoneNo: " + row.getPhoneNum() + " already exists.");
			} else if ((sourceSystem.equalsIgnoreCase(Constants.APSIS_SOURCE_TYPE) || sourceSystem.equalsIgnoreCase(Constants.MAGENTO_SOURCE_TYPE)
					|| sourceSystem.equalsIgnoreCase(Constants.REEDERID_SOURCE_TYPE) || sourceSystem.equalsIgnoreCase(Constants.ZENDESK_SOURCE_TYPE))
					&& row.getEmail() != null && isCustomerDuplicate("email", row.getEmail())) {
				LOGGER.info("Duplicate row: A user with Email: " + row.getEmail() + " already exists.");
				errorRows.add(row);
			} else {
				try {
					jdbcTemplate.update(sql, new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {

							ps.setString(1, row.getId());
							ps.setString(2, row.getName());
							ps.setString(3, row.getPassword());
							ps.setString(4, row.getEmail());
							ps.setString(5, row.getMobileNum());
							if (row.getBirthDate() == null) {
								ps.setNull(6, java.sql.Types.DATE);
							} else {
								ps.setDate(6, getDate(row.getBirthDate(), "dd-MM-yyyy"));
							}
							ps.setString(7, row.getGender());
							ps.setString(8, row.getCreationDate());
							ps.setString(9, row.getIpAddress());
							// Country is hardcoded
							ps.setString(10, "Turkey");
							ps.setString(11, row.getCity());
							Timestamp ts = getTimestamp(null);
							ps.setTimestamp(12, ts);
							ps.setTimestamp(13, ts);
							// Replace this from the value from spring security
							// context
							ps.setInt(14, 1);
							ps.setString(15, row.getSource());
							ps.setString(16, row.getZip());
							ps.setString(17, row.getPhoneNum());
							ps.setString(18, row.getLocation());
							ps.setString(19, row.getLastLogin());
							if (row.getStateName() != null) {
								State state = stateDao.findByName(row.getStateName());
								if (state != null) {
									ps.setInt(20, state.getStateId());
								} else {
									ps.setNull(20, java.sql.Types.INTEGER);
								}
							} else {
								ps.setNull(20, java.sql.Types.INTEGER);
							}
						}
					});
				} catch (DataAccessException ex) {
					LOGGER.error("Exception occurred while saving the row. Row: " + row + " Exception : " + ex);
				}
			}
		}
		LOGGER.info("********************** saveRows() end: Total  " + (rows.size() - errorRows.size()) + " rows inserted. Total " + errorRows.size()
				+ " failed due to duplicacy");
		return errorRows;
	}

	@Override
	public void save(CustomerDetail customerDetail) throws DataAccessException {
		String sql = "UPDATE customerdetail SET name = ?, password = ?, email = ?, phoneNum = ?, birthDate= ?, gender = ?, ipAddress = ?, country = ?, city = ?, "
				+ " updateDate = ?, zip = ?, stateId = ? WHERE customerDetailId = ?";
		Date birthDate = null;
		if (customerDetail.getBirthDate() != null && !customerDetail.getBirthDate().equals("")) {
			birthDate = getDate(customerDetail.getBirthDate(), "dd-MM-yyyy");
		}
		jdbcTemplate.update(sql,
				new Object[] { customerDetail.getName(), customerDetail.getPassword(), customerDetail.getEmail(), customerDetail.getPhoneNum(),
						birthDate, customerDetail.getGender(), customerDetail.getIpAddress(), customerDetail.getCountry(), customerDetail.getCity(),
						getTimestamp(null), customerDetail.getZip(), customerDetail.getStateId() == 0 ? null : customerDetail.getStateId(),
						customerDetail.getCustomerDetailId() });
	}

	public boolean isCustomerDuplicate(String paramType, String param, int cusDetailId) {
		String query = null;
		if (paramType.equals("email")) {
			query = "SELECT count(customerDetailId) FROM customerdetail WHERE email = ? AND customerDetailId <> ?";
		} else if (paramType.equals("phone")) {
			query = "SELECT count(customerDetailId) FROM customerdetail WHERE phoneNum = ? AND customerDetailId <> ?";
		}

		Integer id = jdbcTemplate.queryForObject(query, new Object[] { param, cusDetailId }, Integer.class);
		if (param.equals("")) {
			return false;
		} else {
			return id > 0 ? true : false;
		}

	}

	private boolean isCustomerDuplicate(String paramType, String param) {
		String query = null;
		if (paramType.equals("email")) {
			query = "SELECT count(customerDetailId) FROM customerdetail WHERE email = ?";
		} else if (paramType.equals("phone")) {
			query = "SELECT count(customerDetailId) FROM customerdetail WHERE phoneNum = ?";
		}

		Integer id = jdbcTemplate.queryForObject(query, new Object[] { param }, Integer.class);
		if (param.equals("")) {
			return false;
		} else {
			return id > 0 ? true : false;
		}

	}

	private static Date getDate(String dateString, String dateFormat) {
		DateFormat format = new SimpleDateFormat(dateFormat);
		java.util.Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			LOGGER.error("Invalid date format: Date " + dateString + ". Exception : " + e);
			return null;
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
				LOGGER.error("Invalid datetime format: Date " + timestamp + ". Exception : " + e);
				return null;
			}
		}
		return new java.sql.Timestamp(date.getTime());
	}

	private static String convertDateString(String date){
		//Input is yyyy-MM-dd output should be dd-MM-YYYY
		String finalDate = date.substring(8) + date.substring(4,8) + date.substring(0,4);
		return finalDate;
	}
}
