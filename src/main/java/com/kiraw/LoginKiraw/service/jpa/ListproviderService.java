package com.kiraw.LoginKiraw.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.IListprovidersDao;
import com.kiraw.LoginKiraw.repository.IPublicationDao;
import com.kiraw.LoginKiraw.service.IListproviderService;
import com.kiraw.LoginKiraw.service.IPublicationService;
@Service
public class ListproviderService  implements IListproviderService{
	@Autowired
	private IListprovidersDao iListprovidersDao;

	public List<Provider> findAll() {
		
		return (List<Provider>) iListprovidersDao.findAll();
	}
}
