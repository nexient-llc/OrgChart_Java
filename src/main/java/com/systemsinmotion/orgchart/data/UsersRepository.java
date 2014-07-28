package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Users;

@Repository("userRepository")
public interface UsersRepository extends JpaRepository<Users, String> {

	Users findByUserName(String username);

	Users findByUserPassword(String password);

	List<Users> findByEnabledIsTrue();

}
