package com.jctiru.lnshop.api.ui.model.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.jctiru.lnshop.api.shared.ValidImage;

public class LightNovelDetailsRequestModel {

	@NotBlank(message = "Title must not be empty")
	private String title;

	@NotBlank(message = "Description must not be empty")
	private String description;

	@NotNull(message = "Please put at least 1 genre")
	private List<String> genresIdList;

	@DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than $0.00")
	@DecimalMax(value = "999.99", inclusive = true, message = "Price must be less than $999.99")
	@Digits(integer = 3, fraction = 2, message = "Price max 3 integer and 2 decimal places.")
	@NotNull(message = "Price must be greater than $0.00")
	private BigDecimal price;

	@Min(value = 100, message = "Minimum quantity is 100")
	@Max(value = 1000000, message = "Maximum quantity is 1000000")
	@Digits(integer = 7, fraction = 0, message = "Quantity must be whole number")
	private long quantity;

	@ValidImage
	private MultipartFile image;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getGenresIdList() {
		return genresIdList;
	}

	public void setGenresIdList(List<String> genresIdList) {
		this.genresIdList = genresIdList;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
