package com.systemsinmotion.orgchart.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.systemsinmotion.orgchart.entity.BaseEntity;
import com.systemsinmotion.orgchart.entity.Employee;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {
	
	// TODO : Fix this!
	List<T> findByIsActiveIsTrue();
}
