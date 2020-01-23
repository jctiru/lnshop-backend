package com.jctiru.lnshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesFile {

	private Environment env;

	@Autowired
	public AppPropertiesFile(Environment env) {
		this.env = env;
	}

	public String getTokenSecret() {
		return env.getProperty("jwt.tokensecret");
	}

}
