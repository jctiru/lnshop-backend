package com.jctiru.lnshop.api;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.jctiru.lnshop.api.io.entity.OrderEntity;
import com.jctiru.lnshop.api.shared.dto.OrderDto;

@Configuration
public class BeanDefinitions {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		TypeMap<OrderEntity, OrderDto> orderOverviewTypeMap = modelMapper.createTypeMap(OrderEntity.class,
				OrderDto.class, "OrderOverviewTypeMap",
				modelMapper.getConfiguration().copy().setImplicitMappingEnabled(false));
		orderOverviewTypeMap.addMappings(mapper -> {
			mapper.map(OrderEntity::getOrderId, OrderDto::setOrderId);
			mapper.map(OrderEntity::getCreateDateTime, OrderDto::setCreateDateTime);
			mapper.map(OrderEntity::getCard, OrderDto::setCard);
			mapper.map(OrderEntity::getTotal, OrderDto::setTotal);
		});

		return modelMapper;
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
