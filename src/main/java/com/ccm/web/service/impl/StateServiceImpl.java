package com.ccm.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.web.dao.StateDao;
import com.ccm.web.entity.State;
import com.ccm.web.service.StateService;

@Service
public class StateServiceImpl implements StateService {
	
	@Resource
	private StateDao stateDao;

	@Override
	public List<State> getAllStates() {
		return stateDao.getAllStates();
	}

}
