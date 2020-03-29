package com.jctiru.lnshop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jctiru.lnshop.api.service.AmazonS3ClientService;

@Component
@ConditionalOnProperty(name = "app.aws.s3.privatize-objects", havingValue = "true")
public class AWSS3PrivatizeObjects implements CommandLineRunner {

	@Autowired
	AmazonS3ClientService amazonS3ClientService;

	Logger logger = LoggerFactory.getLogger(AWSS3PrivatizeObjects.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info("Removing ACL in AWS S3 Objects...");

		amazonS3ClientService.privatizeS3Objects();

		logger.info("Finished removing ACL in AWS S3 Objects...");
	}

}
