package com.jctiru.lnshop.api.service;

import java.util.List;

import com.jctiru.lnshop.api.shared.dto.LightNovelDto;

public interface LightNovelService {

	LightNovelDto createLightNovel(LightNovelDto lightNovel);

	LightNovelDto getLightNovelByLightNovelId(String lightNovelId);

	void deleteLightNovel(String lightNovelId);

	List<LightNovelDto> getLightNovels(int page, int limit);

}
