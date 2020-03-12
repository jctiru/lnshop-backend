package com.jctiru.lnshop.api.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jctiru.lnshop.api.service.GenreService;
import com.jctiru.lnshop.api.service.LightNovelService;
import com.jctiru.lnshop.api.shared.dto.GenreDto;
import com.jctiru.lnshop.api.shared.dto.LightNovelDto;
import com.jctiru.lnshop.api.shared.dto.LightNovelPageDto;
import com.jctiru.lnshop.api.ui.model.request.LightNovelDetailsRequestModel;
import com.jctiru.lnshop.api.ui.model.response.GenreRest;
import com.jctiru.lnshop.api.ui.model.response.LightNovelPageRest;
import com.jctiru.lnshop.api.ui.model.response.LightNovelRest;
import com.jctiru.lnshop.api.ui.model.response.OperationStatusModel;
import com.jctiru.lnshop.api.ui.model.response.RequestOperationName;
import com.jctiru.lnshop.api.ui.model.response.RequestOperationResult;

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
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		LightNovelDto lightNovelDto = modelMapper.map(lightNovelDetails, LightNovelDto.class);
		LightNovelDto createdLightNovel = lightNovelService.createLightNovel(lightNovelDto);

		return modelMapper.map(createdLightNovel, LightNovelRest.class);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(path = "/{lightNovelId}")
	public LightNovelRest updateLightNovel(@Valid @ModelAttribute LightNovelDetailsRequestModel lightNovelDetails,
			@PathVariable String lightNovelId) {
		// Standard Strategy tries to map List<String> genresIdList to long id
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		LightNovelDto lightNovelDto = modelMapper.map(lightNovelDetails, LightNovelDto.class);
		LightNovelDto updatedLightNovel = lightNovelService.updateLightNovel(lightNovelId, lightNovelDto);

		return modelMapper.map(updatedLightNovel, LightNovelRest.class);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(path = "/{lightNovelId}")
	public OperationStatusModel deleteLightNovel(@PathVariable String lightNovelId) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		lightNovelService.deleteLightNovel(lightNovelId);
		returnValue.setOperationResult(RequestOperationResult.SUCCESS.name());

		return returnValue;
	}

	@GetMapping
	public LightNovelPageRest getLightNovels(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "8") int limit,
			@RequestParam(value = "genres", defaultValue = "") List<String> genres,
			@RequestParam(value = "search", defaultValue = "") String search) {
		LightNovelPageDto lightNovelPageDto = lightNovelService.getLightNovels(page, limit, genres, search);

		return modelMapper.map(lightNovelPageDto, LightNovelPageRest.class);
	}

	@GetMapping(path = "/{lightNovelId}")
	public LightNovelRest getLightNovel(@PathVariable String lightNovelId) {
		LightNovelDto lightNovelDto = lightNovelService.getLightNovelByLightNovelId(lightNovelId);
		return modelMapper.map(lightNovelDto, LightNovelRest.class);
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
