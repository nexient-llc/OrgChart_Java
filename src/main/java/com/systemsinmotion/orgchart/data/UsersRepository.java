package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

	Users findByUserName(String username);

	Users findByUserPassword(String password);

	List<Users> findByEnabledIsTrue();

}
