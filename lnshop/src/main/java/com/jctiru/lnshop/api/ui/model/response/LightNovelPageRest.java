package com.jctiru.lnshop.api.ui.model.response;

import java.util.List;

public class LightNovelPageRest {

	private int totalPages;
	private List<LightNovelRest> lightNovels;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<LightNovelRest> getLightNovels() {
		return lightNovels;
	}

	public void setLightNovels(List<LightNovelRest> lightNovels) {
		this.lightNovels = lightNovels;
	}

}
