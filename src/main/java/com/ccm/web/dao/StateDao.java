package com.ccm.web.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ccm.web.entity.State;

public interface StateDao {
	List<State> getAllStates() throws DataAccessException;
	State findByName(String name) throws DataAccessException;
}
