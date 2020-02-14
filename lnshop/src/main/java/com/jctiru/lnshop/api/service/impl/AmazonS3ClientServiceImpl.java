package com.jctiru.lnshop.api.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jctiru.lnshop.api.service.AmazonS3ClientService;

@Service
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService {

	@Autowired
	AmazonS3 amazonS3;

	@Value("${app.aws.s3.bucket}")
	String awsS3Bucket;

	@Override
	public String uploadFileToS3Bucket(MultipartFile multipartFile, String stringPrefix) {
		String fileName = stringPrefix + multipartFile.getOriginalFilename();

		// Create temporary file
		File file = new File(fileName);

		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());

			PutObjectRequest putObjectRequest = new PutObjectRequest(awsS3Bucket, fileName, file);
			putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Delete temporary file
			file.delete();
		}

		return ((AmazonS3Client) amazonS3).getResourceUrl(awsS3Bucket, fileName);
	}

	@Override
	public void deleteFileFromS3Bucket(String fileName) {
		try {
			amazonS3.deleteObject(new DeleteObjectRequest(awsS3Bucket, fileName));
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}

	@Override
	public String getFileNameFromImageUrl(String imageUrl) {
		return imageUrl.replace("https://" + awsS3Bucket + ".s3.amazonaws.com/", "");
	}

}
