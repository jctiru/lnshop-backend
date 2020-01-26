package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jctiru.lnshop.api.io.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

	GenreEntity findGenreByName(String name);

	boolean existsGenreByName(String name);

}
