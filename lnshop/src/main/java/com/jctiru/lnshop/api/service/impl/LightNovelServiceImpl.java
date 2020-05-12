package com.jctiru.lnshop.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.jctiru.lnshop.api.shared.dto.LightNovelPageDto;

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

	@Transactional
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
			CompletableFuture<String> imageUrl = amazonS3ClientService.uploadFileToS3Bucket(lightNovel.getImage(),
					publicLightNovelId);
			try {
				lightNovelEntity.setImageUrl(imageUrl.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		LightNovelEntity storedLightNovelDetails = lightNovelRepository.save(lightNovelEntity);

		return modelMapper.map(storedLightNovelDetails, LightNovelDto.class);
	}

	@Transactional
	@Override
	public LightNovelDto updateLightNovel(String lightNovelId, LightNovelDto lightNovel) {
		LightNovelEntity lightNovelEntity = lightNovelRepository.findByLightNovelId(lightNovelId);

		if (lightNovelEntity == null) {
			throw new RecordNotFoundException(lightNovelId + " not found.");
		}

		Set<GenreEntity> genres = new HashSet<>();

		for (String genreId : lightNovel.getGenresIdList()) {
			GenreEntity genreEntity = genreRepository.findByGenreId(genreId);

			if (genreEntity == null) {
				throw new RecordNotFoundException(genreId + " not found.");
			}

			genres.add(genreEntity);
		}

		lightNovelEntity.setGenres(genres);
		lightNovelEntity.setTitle(lightNovel.getTitle());
		lightNovelEntity.setDescription(lightNovel.getDescription());
		lightNovelEntity.setPrice(lightNovel.getPrice());
		lightNovelEntity.setQuantity(lightNovel.getQuantity());

		if (lightNovel.getImage() != null) {
			if (lightNovelEntity.getImageUrl() != null) {
				String fileName = amazonS3ClientService.getFileNameFromImageUrl(lightNovelEntity.getImageUrl());
				amazonS3ClientService.deleteFileFromS3Bucket(fileName);
			}

			CompletableFuture<String> imageUrl = amazonS3ClientService.uploadFileToS3Bucket(lightNovel.getImage(),
					lightNovelEntity.getLightNovelId());
			try {
				lightNovelEntity.setImageUrl(imageUrl.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		LightNovelEntity updatedLightNovel = lightNovelRepository.save(lightNovelEntity);

		return modelMapper.map(updatedLightNovel, LightNovelDto.class);
	}

	@Transactional
	@Override
	public LightNovelDto getLightNovelByLightNovelId(String lightNovelId) {
		LightNovelEntity lightNovelEntity = lightNovelRepository.findByLightNovelId(lightNovelId);

		if (lightNovelEntity == null) {
			throw new RecordNotFoundException(lightNovelId + " not found.");
		}

		return modelMapper.map(lightNovelEntity, LightNovelDto.class);
	}

	@Transactional
	@Override
	public void deleteLightNovel(String lightNovelId) {
		LightNovelEntity lightNovelEntity = lightNovelRepository.findByLightNovelId(lightNovelId);

		if (lightNovelEntity == null) {
			throw new RecordNotFoundException(lightNovelId + " not found.");
		}

		if (lightNovelEntity.getImageUrl() != null) {
			String fileName = amazonS3ClientService.getFileNameFromImageUrl(lightNovelEntity.getImageUrl());
			amazonS3ClientService.deleteFileFromS3Bucket(fileName);
		}

		lightNovelRepository.delete(lightNovelEntity);
	}

	@Transactional
	@Override
	public LightNovelPageDto getLightNovels(int page, int limit, List<String> genres, String search) {
		if (page > 0) {
			page = page - 1;
		}

		Pageable pageable = PageRequest.of(page, limit, Sort.by("createDateTime").descending());
		Page<LightNovelEntity> lightNovelsPage;

		if (genres.isEmpty() && search.isBlank()) {
			lightNovelsPage = lightNovelRepository.findAll(pageable);
		} else if (genres.isEmpty() && !search.isBlank()) {
			lightNovelsPage = lightNovelRepository.findAllByTitleContainingIgnoreCase(search, pageable);
		} else if (!genres.isEmpty() && search.isBlank()) {
			lightNovelsPage = lightNovelRepository.findAllByGenresName(genres, pageable);
		} else {
			lightNovelsPage = lightNovelRepository.findAllByGenresAndSearch(genres, search, pageable);
		}

		List<LightNovelEntity> lightNovels = lightNovelsPage.getContent();
		List<LightNovelDto> lightNovelsDto = new ArrayList<>();

		for (LightNovelEntity lightNovelEntity : lightNovels) {
			LightNovelDto lightNovelDto = modelMapper.map(lightNovelEntity, LightNovelDto.class);
			lightNovelsDto.add(lightNovelDto);
		}

		LightNovelPageDto returnValue = new LightNovelPageDto();
		returnValue.setTotalPages(lightNovelsPage.getTotalPages());
		returnValue.setLightNovels(lightNovelsDto);

		return returnValue;
	}

}
