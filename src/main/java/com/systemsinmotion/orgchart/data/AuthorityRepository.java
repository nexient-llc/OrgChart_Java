package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Authorities;

@Repository("authorityRepository")
public interface AuthorityRepository extends JpaRepository<Authorities, String> {

	List<Authorities> findByAuthority(String auth);

	Authorities findByUserName(String username);

}
