package com.systemsinmotion.orgchart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.dao.RoleDao;
import com.systemsinmotion.orgchart.entity.Role;

@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private RoleDao roleDao;

	public Role getRole(int id) {
		return roleDao.getRole(id);
	}

}
