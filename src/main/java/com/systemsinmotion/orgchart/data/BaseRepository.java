package com.systemsinmotion.orgchart.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.systemsinmotion.orgchart.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {
	List<T> findByIsActiveIsTrue();
	
	List<T> findAll();
	
	void delete(T entity);
	
	void delete(ID id);
	
	T findOne(ID id);
}
