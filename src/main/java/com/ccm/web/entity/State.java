package com.ccm.web.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class State implements RowMapper<State> {
	private int stateId;
	private String stateName;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}

	@Override
	public State mapRow(ResultSet rs, int rowNum) throws SQLException {
		State state = new State();
		state.setStateId(rs.getInt(1));
		state.setStateName(rs.getString(2));
		return state;
	}

}
