package com.jctiru.lnshop.api.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CanonicalGrantee;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jctiru.lnshop.api.service.AmazonS3ClientService;

@Service
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService {

	@Autowired
	AmazonS3 amazonS3;

	@Value("${app.aws.s3.bucket}")
	String awsS3Bucket;

	@Value("${app.aws.cloudfront.cname}")
	String awsCloudfrontCname;

	Logger logger = LoggerFactory.getLogger(AmazonS3ClientServiceImpl.class);

	@Async
	@Override
	public CompletableFuture<String> uploadFileToS3Bucket(MultipartFile multipartFile, String stringPrefix) {
		String fileName = stringPrefix + multipartFile.getOriginalFilename();

		// Create temporary file
		File file = new File(fileName);

		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
			PutObjectRequest putObjectRequest = new PutObjectRequest(awsS3Bucket, fileName, file);
			amazonS3.putObject(putObjectRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Delete temporary file
			file.delete();
		}

		return CompletableFuture.completedFuture(((AmazonS3Client) amazonS3).getResourceUrl(awsS3Bucket, fileName));
	}

	@Async
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

	@Override
	public String convertS3UrlToCloudfrontCnameUrl(String imageUrl) {
		return "https://" + awsCloudfrontCname + "/" + this.getFileNameFromImageUrl(imageUrl);
	}

	@Override
	public void privatizeS3Objects() {
		S3Objects.inBucket(amazonS3, awsS3Bucket).forEach((S3ObjectSummary objectSummary) -> {
			logger.info("Modifying ACL of {}...", objectSummary.getKey());

			AccessControlList acl = amazonS3.getObjectAcl(awsS3Bucket, objectSummary.getKey());
			acl.getGrantsAsList().clear();
			acl.grantPermission(new CanonicalGrantee(acl.getOwner().getId()), Permission.FullControl);
			amazonS3.setObjectAcl(awsS3Bucket, objectSummary.getKey(), acl);

			logger.info("Successfully modified {}...", objectSummary.getKey());
		});

	}

}
