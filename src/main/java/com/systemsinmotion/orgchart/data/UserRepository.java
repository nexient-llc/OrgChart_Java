package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.User;

public interface UserRepository extends JpaRepository<User,String>{
	
	User findByUserNameIgnoreCase(String userName);
	
	List<User> findByEnabledIsTrue();
}
