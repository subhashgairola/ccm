package com.ccm.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ccm.web.entity.State;

public interface StateService {
	List<State> getAllStates() throws DataAccessException;
}
