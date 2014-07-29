package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.AuthorityRepository;
import com.systemsinmotion.orgchart.entity.Authorities;

@Service("authorityService")
public class AuthoritiesService {

	@Autowired
	private AuthorityRepository authorityRepository;

	List<Authorities> findAllByAuthority(String auth) {
		return this.authorityRepository.findByAuthority(auth);
	}

	Authorities findUserByUserName(String username) {
		return this.authorityRepository.findByUserName(username);
	}

}
