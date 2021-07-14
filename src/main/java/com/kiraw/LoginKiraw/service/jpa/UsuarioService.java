package com.kiraw.LoginKiraw.service.jpa;

import org.springframework.stereotype.Service;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;
import com.kiraw.LoginKiraw.repository.IProveedorDao;
import com.kiraw.LoginKiraw.repository.IProviderDao;
import com.kiraw.LoginKiraw.repository.IUsuarioDao;
import com.kiraw.LoginKiraw.service.IUsuarioService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;
import javax.persistence.Entity;

@Service
public class UsuarioService implements UserDetailsService,IUsuarioService{
	
	private Logger logger=LoggerFactory.getLogger(UsuarioService.class);
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override		
	@Transactional()
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario=usuarioDao.findByUsername(username);
		if(usuario==null & usuario.getPassword()==null) {
			logger.error("error en el login no existe el usuario"+username+"en el sistema");

		}

		
	List<GrantedAuthority> authorities=usuario.getRoles()
			.stream()
			.map(role->new SimpleGrantedAuthority(role.getName()))
		
			
			.peek(authority->logger.info("Role"+authority.getAuthority()))
			.collect(Collectors.toList());
		
	
		return new User(usuario.getUsername(),usuario.getPassword(), usuario.getEnable(), true, true, true,   authorities);
		
	
	}

	@Override
	public Users findByUsername(String username) {
	
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Provider findProviderByUsersId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	


	
	
}
