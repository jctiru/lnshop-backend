package com.jctiru.lnshop.api;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jctiru.lnshop.api.io.entity.GenreEntity;
import com.jctiru.lnshop.api.io.repository.GenreRepository;

@Component
@ConditionalOnProperty(name = "app.db-role-init", havingValue = "true")
public class DbGenreInitializer implements CommandLineRunner {

	@Autowired
	GenreRepository genreRepository;

	Logger logger = LoggerFactory.getLogger(DbGenreInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info("Initilizing DB Roles...");

		List<String> genreList = Arrays.asList(
				"Action",
				"Adventure",
				"Comedy",
				"Drama",
				"Ecchi",
				"Harem",
				"Horror",
				"Mystery",
				"Psychological",
				"Romance",
				"Sci-Fi",
				"Slice of Life",
				"Supernatural");

		for (String genre : genreList) {
			if (!genreRepository.existsGenreByName(genre)) {
				GenreEntity genreEntity = new GenreEntity();
				genreEntity.setName(genre);
				genreRepository.save(genreEntity);
				logger.info("{} genre not found in DB Genres and has been created...", genre);
			} else {
				logger.info("{} genre found in DB Genres...", genre);
			}
		}

	}

}
