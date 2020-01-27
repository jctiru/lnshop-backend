package com.jctiru.lnshop.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jctiru.lnshop.api.exception.RecordAlreadyExistsException;
import com.jctiru.lnshop.api.exception.RecordNotFoundException;
import com.jctiru.lnshop.api.io.entity.GenreEntity;
import com.jctiru.lnshop.api.io.repository.GenreRepository;
import com.jctiru.lnshop.api.service.GenreService;
import com.jctiru.lnshop.api.shared.Utils;
import com.jctiru.lnshop.api.shared.dto.GenreDto;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public GenreDto createGenre(GenreDto genre) {
		if (genreRepository.existsGenreByName(genre.getName())) {
			throw new RecordAlreadyExistsException(genre.getName() + " already exists.");
		}

		GenreEntity genreEntity = modelMapper.map(genre, GenreEntity.class);

		String publicGenreId = utils.generatePublicEntityId(Utils.EntityType.GENRE);
		genreEntity.setGenreId(publicGenreId);

		GenreEntity storedGenreDetails = genreRepository.save(genreEntity);

		return modelMapper.map(storedGenreDetails, GenreDto.class);
	}

	@Override
	public GenreDto getGenreByName(String name) {
		GenreEntity genreEntity = genreRepository.findGenreByName(name);

		if (genreEntity == null) {
			throw new RecordNotFoundException(name + " not found.");
		}

		return modelMapper.map(genreEntity, GenreDto.class);
	}

	@Override
	public GenreDto getGenreByGenreId(String genreId) {
		GenreEntity genreEntity = genreRepository.findByGenreId(genreId);

		if (genreEntity == null) {
			throw new RecordNotFoundException(genreId + " not found.");
		}

		return modelMapper.map(genreEntity, GenreDto.class);
	}

	@Override
	public List<GenreDto> getGenres() {
		List<GenreDto> returnValue = new ArrayList<>();
		List<GenreEntity> genres = genreRepository.findAll();

		for (GenreEntity genreEntity : genres) {
			GenreDto genreDto = modelMapper.map(genreEntity, GenreDto.class);
			returnValue.add(genreDto);
		}

		return returnValue;
	}

}
