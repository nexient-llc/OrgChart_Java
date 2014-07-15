package com.systemsinmotion.orgchart.data;

import java.io.Serializable;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>  extends JpaRepository<T,ID>{
	
	List<T> findByIsActiveIsTrue();
	
}
