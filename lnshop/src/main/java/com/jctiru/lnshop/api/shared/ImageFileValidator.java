package com.jctiru.lnshop.api.shared;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

		String contentType = multipartFile.getContentType();
		long maxFileSize = 350000; // 350KB

		if (!multipartFile.isEmpty()) {
			if (!isSupportedContentType(contentType)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Only PNG or JPG images are allowed.")
						.addConstraintViolation();
				return false;
			}

			if (multipartFile.getSize() > maxFileSize) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Max image file size is 350KB")
						.addConstraintViolation();
				return false;
			}
		}

		return true;
	}

	private boolean isSupportedContentType(String contentType) {
		return contentType.equals("image/png") || contentType.equals("image/jpg") || contentType.equals("image/jpeg");
	}

}
