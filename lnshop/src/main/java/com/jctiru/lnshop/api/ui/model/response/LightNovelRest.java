package com.jctiru.lnshop.api.ui.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class LightNovelRest {

	private String lightNovelId;
	private String title;
	private String description;
	private Set<GenreRest> genres;
	private BigDecimal price;
	private long quantity;
	private long sold;
	private String imageUrl;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;

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

	public Set<GenreRest> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreRest> genres) {
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
