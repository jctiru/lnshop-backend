package com.jctiru.lnshop.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class BeanDefinitions {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public AmazonS3 amazonS3Client(AWSCredentialsProvider awsCredentialsProvider,
			@Value("${cloud.aws.region.static}") String region) {
		return AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider).withRegion(region).build();
	}

	@Bean
	public AmazonSimpleEmailService amazonSESClient(AWSCredentialsProvider awsCredentialsProvider,
			@Value("${cloud.aws.region.static}") String region) {
		return AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(awsCredentialsProvider)
				.withRegion(region).build();
	}

}
