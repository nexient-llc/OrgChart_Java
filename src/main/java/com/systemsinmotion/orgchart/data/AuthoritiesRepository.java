package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities,String> {

	Authorities findByUserNameIgnoreCase(String userName);
	
	List<Authorities> findByAuthority(String authority);
}
