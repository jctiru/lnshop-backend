package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jctiru.lnshop.api.io.entity.LightNovelEntity;

@Repository
public interface LightNovelRepository extends JpaRepository<LightNovelEntity, Long> {

	LightNovelEntity findByLightNovelId(String lightNovelId);

}
