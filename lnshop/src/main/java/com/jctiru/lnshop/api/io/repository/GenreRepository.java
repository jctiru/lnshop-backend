package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jctiru.lnshop.api.io.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

	GenreEntity findGenreByName(String name);

	GenreEntity findByGenreId(String genreId);

	boolean existsGenreByName(String name);

}
