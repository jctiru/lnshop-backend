package com.jctiru.lnshop.api.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jctiru.lnshop.api.service.GenreService;
import com.jctiru.lnshop.api.service.LightNovelService;
import com.jctiru.lnshop.api.shared.dto.GenreDto;
import com.jctiru.lnshop.api.shared.dto.LightNovelDto;
import com.jctiru.lnshop.api.ui.model.request.LightNovelDetailsRequestModel;
import com.jctiru.lnshop.api.ui.model.response.GenreRest;
import com.jctiru.lnshop.api.ui.model.response.LightNovelRest;

@RestController
@RequestMapping("lightnovels")
public class LightNovelRestController {

	@Autowired
	LightNovelService lightNovelService;

	@Autowired
	GenreService genreService;

	@Autowired
	ModelMapper modelMapper;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public LightNovelRest createLightNovel(@Valid @ModelAttribute LightNovelDetailsRequestModel lightNovelDetails) {
		// Standard Strategy tries to map List<String> genresIdList to long id
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT);
		LightNovelDto lightNovelDto = modelMapper.map(lightNovelDetails, LightNovelDto.class);
		LightNovelDto createdLightNovel = lightNovelService.createLightNovel(lightNovelDto);

		return modelMapper.map(createdLightNovel, LightNovelRest.class);
	}

	@GetMapping
	public List<LightNovelRest> getLightNovels(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<LightNovelRest> returnValue = new ArrayList<>();
		List<LightNovelDto> lightNovels = lightNovelService.getLightNovels(page, limit);

		for (LightNovelDto lightNovelDto : lightNovels) {
			LightNovelRest lightNovelModel = modelMapper.map(lightNovelDto, LightNovelRest.class);
			returnValue.add(lightNovelModel);
		}

		return returnValue;
	}

	@GetMapping("/genres")
	public List<GenreRest> getGenres() {
		List<GenreRest> returnValue = new ArrayList<>();
		List<GenreDto> genres = genreService.getGenres();

		for (GenreDto genreDto : genres) {
			GenreRest genreModel = modelMapper.map(genreDto, GenreRest.class);
			returnValue.add(genreModel);
		}

		return returnValue;
	}

}
