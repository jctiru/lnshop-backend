package com.jctiru.lnshop.api;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDefinitions {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
}
