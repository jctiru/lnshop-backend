package com.jctiru.lnshop.api;

import java.util.concurrent.Executor;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.jctiru.lnshop.api.io.entity.LightNovelEntity;
import com.jctiru.lnshop.api.io.entity.OrderEntity;
import com.jctiru.lnshop.api.service.AmazonS3ClientService;
import com.jctiru.lnshop.api.shared.dto.LightNovelDto;
import com.jctiru.lnshop.api.shared.dto.OrderDto;

@Configuration
public class BeanDefinitions {

	@Bean
	public ModelMapper modelMapper(AmazonS3ClientService amazonS3ClientService) {
		ModelMapper modelMapper = new ModelMapper();

		// Specify only required fields in order overview when mapping OrderEntity to
		// OrderDto
		TypeMap<OrderEntity, OrderDto> orderOverviewTypeMap = modelMapper.createTypeMap(OrderEntity.class,
				OrderDto.class, "OrderOverviewTypeMap",
				modelMapper.getConfiguration().copy().setImplicitMappingEnabled(false));
		orderOverviewTypeMap.addMappings(mapper -> {
			mapper.map(OrderEntity::getOrderId, OrderDto::setOrderId);
			mapper.map(OrderEntity::getCreateDateTime, OrderDto::setCreateDateTime);
			mapper.map(OrderEntity::getCard, OrderDto::setCard);
			mapper.map(OrderEntity::getTotal, OrderDto::setTotal);
		});

		// Convert S3 Url to Cloudfront CNAME url when mapping LightNovelEntity to
		// LightNovelDto
		Converter<String, String> convertS3UrlToCloudfrontCnameUrl = context -> context.getSource() == null ? null
				: amazonS3ClientService.convertS3UrlToCloudfrontCnameUrl(context.getSource());
		modelMapper.typeMap(LightNovelEntity.class, LightNovelDto.class)
				.addMappings(mapper -> mapper.using(convertS3UrlToCloudfrontCnameUrl).map(LightNovelEntity::getImageUrl,
						LightNovelDto::setImageUrl));

		return modelMapper;
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setAllowCoreThreadTimeOut(true);

		return executor;
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
