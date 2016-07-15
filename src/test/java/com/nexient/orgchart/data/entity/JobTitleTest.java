package com.nexient.orgchart.data.entity;

import com.nexient.validation.Validator;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Created by kskronek on 6/8/2016.
 */
public class JobTitleTest {

	@Test
	public void testStuff(){
		JobTitle jobTitle = new JobTitle.Builder("description").employees(Collections.emptySet()).build();

		Validator validator = new Validator();
		validator.validate(jobTitle);
	}
}
