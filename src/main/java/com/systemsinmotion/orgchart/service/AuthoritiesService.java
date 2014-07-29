package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.AuthoritiesRepository;
import com.systemsinmotion.orgchart.entity.Authorities;

@Service("authoritiesService")
@Transactional(readOnly = true)
public class AuthoritiesService {

	@Autowired
	AuthoritiesRepository repo;

	public Authorities findAuthorityByName(String userName) {
		return repo.findByUserNameIgnoreCase(userName);
	}

	public List<Authorities> findAuthoritiesByAuthority(String auth) {
		return repo.findByAuthority(auth);
	}

	@Transactional
	public Authorities storeAuthority(Authorities authority) {
		return repo.save(authority);
	}
	
	@Transactional
	public void removeAuthority(Authorities authority) {
		repo.delete(authority);
	}

	public void removeAuthorityByName(String userName) {
		Authorities auth = repo.findByUserNameIgnoreCase(userName);
		removeAuthority(auth);
	}
}
