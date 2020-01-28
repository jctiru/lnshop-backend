package com.jctiru.lnshop.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jctiru.lnshop.api.exception.RecordNotFoundException;
import com.jctiru.lnshop.api.io.entity.GenreEntity;
import com.jctiru.lnshop.api.io.entity.LightNovelEntity;
import com.jctiru.lnshop.api.io.repository.GenreRepository;
import com.jctiru.lnshop.api.io.repository.LightNovelRepository;
import com.jctiru.lnshop.api.service.AmazonS3ClientService;
import com.jctiru.lnshop.api.service.LightNovelService;
import com.jctiru.lnshop.api.shared.Utils;
import com.jctiru.lnshop.api.shared.dto.LightNovelDto;

@Service
public class LightNovelServiceImpl implements LightNovelService {

	@Autowired
	LightNovelRepository lightNovelRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	AmazonS3ClientService amazonS3ClientService;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public LightNovelDto createLightNovel(LightNovelDto lightNovel) {
		LightNovelEntity lightNovelEntity = modelMapper.map(lightNovel, LightNovelEntity.class);

		Set<GenreEntity> genres = new HashSet<>();

		for (String genreId : lightNovel.getGenresIdList()) {
			GenreEntity genreEntity = genreRepository.findByGenreId(genreId);

			if (genreEntity == null) {
				throw new RecordNotFoundException(genreId + " not found.");
			}

			genres.add(genreEntity);
		}

		lightNovelEntity.setGenres(genres);

		String publicLightNovelId = utils.generatePublicEntityId(Utils.EntityType.LIGHTNOVEL);
		lightNovelEntity.setLightNovelId(publicLightNovelId);

		if (lightNovel.getImage() != null) {
			String imageUrl = amazonS3ClientService.uploadFileToS3Bucket(lightNovel.getImage(), publicLightNovelId);
			lightNovelEntity.setImageUrl(imageUrl);
		}

		LightNovelEntity storedLightNovelDetails = lightNovelRepository.save(lightNovelEntity);

		return modelMapper.map(storedLightNovelDetails, LightNovelDto.class);
	}

	@Override
	public LightNovelDto getLightNovelByLightNovelId(String lightNovelId) {
		LightNovelEntity lightNovelEntity = lightNovelRepository.findByLightNovelId(lightNovelId);

		if (lightNovelEntity == null) {
			throw new RecordNotFoundException(lightNovelId + " not found.");
		}

		return modelMapper.map(lightNovelEntity, LightNovelDto.class);
	}

	@Override
	public void deleteLightNovel(String lightNovelId) {
		LightNovelEntity lightNovelEntity = lightNovelRepository.findByLightNovelId(lightNovelId);

		if (lightNovelEntity == null) {
			throw new RecordNotFoundException("");
		}

		lightNovelRepository.delete(lightNovelEntity);
	}

	@Override
	public List<LightNovelDto> getLightNovels(int page, int limit) {
		if (page > 0) {
			page = page - 1;
		}

		List<LightNovelDto> returnValue = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, limit);
		Page<LightNovelEntity> lightNovelsPage = lightNovelRepository.findAll(pageable);
		List<LightNovelEntity> lightNovels = lightNovelsPage.getContent();

		for (LightNovelEntity lightNovelEntity : lightNovels) {
			LightNovelDto lightNovelDto = modelMapper.map(lightNovelEntity, LightNovelDto.class);
			returnValue.add(lightNovelDto);
		}

		return returnValue;
	}

}
