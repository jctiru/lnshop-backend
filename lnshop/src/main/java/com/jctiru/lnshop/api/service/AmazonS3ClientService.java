package com.jctiru.lnshop.api.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3ClientService {

	CompletableFuture<String> uploadFileToS3Bucket(MultipartFile multipartFile, String stringPrefix);

	void deleteFileFromS3Bucket(String fileName);

	String getFileNameFromImageUrl(String imageUrl);

	String convertS3UrlToCloudfrontCnameUrl(String imageUrl);

	void privatizeS3Objects();

}
