package com.kiraw.LoginKiraw.service;


import java.util.List;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface IPublicationService {
	public List<Publication> findByOrderByIdDesc();
	public Publication save(Publication publication);
	public Publication findPublicationById(int id);
	public List<Publication> findByOrderByIdDesc(Pageable pageable);
	public Page<Publication> findAll(Pageable pageable);

	//public List<Publication> findPublicationByIdOrderByIdDesc(Integer id);
	public List<Publication> findPublicationByIdOrderByCommentsDesc(Integer id);
	public List<Publication> findPublicationByProviderId(Integer id);
	public void delete(Integer id);
}
