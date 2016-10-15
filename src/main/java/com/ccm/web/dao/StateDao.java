package com.ccm.web.dao;

import java.util.List;

import com.ccm.web.entity.State;

public interface StateDao {
	List<State> getAllStates();
	State findByName(String name);
}
