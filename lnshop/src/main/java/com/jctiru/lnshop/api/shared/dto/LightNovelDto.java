package com.jctiru.lnshop.api.shared.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.jctiru.lnshop.api.io.entity.GenreEntity;

public class LightNovelDto {

	private long id;
	private String lightNovelId;
	private String title;
	private String description;
	private List<String> genresIdList;
	private Set<GenreEntity> genres;
	private BigDecimal price;
	private long quantity;
	private long sold = 0;
	private MultipartFile image;
	private String imageUrl;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLightNovelId() {
		return lightNovelId;
	}

	public void setLightNovelId(String lightNovelId) {
		this.lightNovelId = lightNovelId;
	}

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

	public Set<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreEntity> genres) {
		this.genres = genres;
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

	public long getSold() {
		return sold;
	}

	public void setSold(long sold) {
		this.sold = sold;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
