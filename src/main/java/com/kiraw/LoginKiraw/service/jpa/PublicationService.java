package com.kiraw.LoginKiraw.service.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.entity.Roles;
import com.kiraw.LoginKiraw.repository.IProviderDao;
import com.kiraw.LoginKiraw.repository.IPublicationDao;
import com.kiraw.LoginKiraw.repository.PublicationRepository;
import com.kiraw.LoginKiraw.service.IPublicationService;
@Service
public class PublicationService implements IPublicationService{
	@Autowired
	private IPublicationDao publicationDao;




	public List<Publication> findPublicationByIdOrderByCommentsDesc(Integer id) {
		return  publicationDao.findPublicationByIdOrderByCommentsDesc(id);
	}

	public List<Publication> findPublicationByProviderId(Integer id) {
		return  publicationDao.findPublicationByProviderIdOrderByIdDesc(id);
	}


	@Override
	public void delete(Integer id) {
	publicationDao.deleteById(id);
	}


	@Override
	public List<Publication> findByOrderByIdDesc() {
		return publicationDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	@Transactional
	public Publication save(Publication publication) {
		
				return publicationDao.save(publication);
	}
	
	@Transactional
	public void delete(int id) {
		publicationDao.deleteById(id);
	}
	@Transactional
	@Override
	public Publication findPublicationById(int id) {
		return publicationDao.findById(id).orElse(null);
	}

	@Override
	public List<Publication> findByOrderByIdDesc(Pageable pageable) {
		return null;
	}


	@Transactional
	public Page<Publication> findAll(Pageable pageable) {
		return publicationDao.findAll(pageable);
	}

	@Transactional
	public Page<Publication> findAll2(Integer id,Pageable pageable) {
		return  publicationDao.findPublicationByProviderIdOrderByIdDesc(id,pageable);
	}

	public List<Publication> findPublicationByIdOrderByIdDesc(Integer id) {
		return null;
	}


	@Autowired
	private PublicationRepository publicationRepository;
	public void crear(Publication publication) {
		publicationRepository.crear(publication);
	}

}
