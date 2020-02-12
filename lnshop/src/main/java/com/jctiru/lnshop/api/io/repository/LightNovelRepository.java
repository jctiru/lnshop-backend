package com.jctiru.lnshop.api.io.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jctiru.lnshop.api.io.entity.LightNovelEntity;

@Repository
public interface LightNovelRepository extends JpaRepository<LightNovelEntity, Long> {

	LightNovelEntity findByLightNovelId(String lightNovelId);

	@Query("select ln from LightNovelEntity ln join ln.genres g where g.name in :genres group by ln having count(ln) >= (select count(g) from GenreEntity g where g.name in :genres)")
	Page<LightNovelEntity> findAllByGenresName(@Param("genres") List<String> genres, Pageable page);

	Page<LightNovelEntity> findAllByTitleContainingIgnoreCase(String title, Pageable page);

}
