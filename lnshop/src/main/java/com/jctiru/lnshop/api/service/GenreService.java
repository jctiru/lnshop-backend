package com.jctiru.lnshop.api.service;

import java.util.List;

import com.jctiru.lnshop.api.shared.dto.GenreDto;

public interface GenreService {

	GenreDto createGenre(GenreDto genre);

	GenreDto getGenreByName(String name);

	GenreDto getGenreByGenreId(String genreId);

	List<GenreDto> getGenres();

}
