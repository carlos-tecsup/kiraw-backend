package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kiraw.LoginKiraw.controller.PublicationController;
import com.kiraw.LoginKiraw.entity.Publication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublicationRepository {
private static final Logger logger = LoggerFactory.getLogger(PublicationController.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	
	
	public void crear(Publication publication) {
		logger.info("crear " + publication);
		
		String sql = "INSERT INTO publication(image_publication,title_publication,title_description,provider_id) values (?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, publication.getImage_publication(), publication.getTitle_publication(), publication.getImage_publication(),publication.getProvider().getId());
	}













}
