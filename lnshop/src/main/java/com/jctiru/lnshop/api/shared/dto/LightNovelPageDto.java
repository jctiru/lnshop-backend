package com.jctiru.lnshop.api.shared.dto;

import java.util.List;

public class LightNovelPageDto {

	private int totalPages;
	private List<LightNovelDto> lightNovels;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<LightNovelDto> getLightNovels() {
		return lightNovels;
	}

	public void setLightNovels(List<LightNovelDto> lightNovels) {
		this.lightNovels = lightNovels;
	}

}
