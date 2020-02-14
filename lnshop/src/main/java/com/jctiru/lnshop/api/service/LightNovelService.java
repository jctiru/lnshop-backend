package com.jctiru.lnshop.api.service;

import java.util.List;

import com.jctiru.lnshop.api.shared.dto.LightNovelDto;
import com.jctiru.lnshop.api.shared.dto.LightNovelPageDto;

public interface LightNovelService {

	LightNovelDto createLightNovel(LightNovelDto lightNovel);

	LightNovelDto getLightNovelByLightNovelId(String lightNovelId);

	void deleteLightNovel(String lightNovelId);

	LightNovelPageDto getLightNovels(int page, int limit, List<String> genres, String search);

	LightNovelDto updateLightNovel(String lightNovelId, LightNovelDto lightNovel);

}
