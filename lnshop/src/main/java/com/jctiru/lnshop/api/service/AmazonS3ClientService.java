package com.jctiru.lnshop.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3ClientService {

	String uploadFileToS3Bucket(MultipartFile multipartFile, String stringPrefix);

	void deleteFileFromS3Bucket(String fileName);

	String getFileNameFromImageUrl(String imageUrl);

}
