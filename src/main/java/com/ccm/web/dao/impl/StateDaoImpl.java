package com.ccm.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ccm.web.dao.StateDao;
import com.ccm.web.entity.State;

@Repository
public class StateDaoImpl implements StateDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<State> getStates() {
		String sql = "SELECT stateId, stateName from states";
		List<State> states = jdbcTemplate.query(sql, new State());
		return states;
	}

	@Override
	public State findByName(String name)  {
		String sql = "SELECT stateId, stateName from states WHERE stateName = ?";
		State state = null;
		try{
			 state =  jdbcTemplate.queryForObject(sql, new Object[]{name}, new State());
		}catch(EmptyResultDataAccessException e){
			
		}
		return state;
	}
}
