package com.nexient.orgchart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by kskronek on 6/8/2016.
 */
@Configuration
public class EntityTestConfig {

	@Bean(name = "validator")
	public Validator getValidator() {
		return new LocalValidatorFactoryBean();
	}

}
