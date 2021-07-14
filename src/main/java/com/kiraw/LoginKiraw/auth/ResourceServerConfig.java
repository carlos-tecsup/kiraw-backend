package com.kiraw.LoginKiraw.auth;


import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableResourceServer

public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests().antMatchers("/api/proveedores","/api/clientes","/api/publicaciones/uploads/img/{nombreFoto:.+}","/api/publicaciones","/api/providers/img/fondo/{nombreFoto:.+}","/api/products/img/{nombreFoto:.+}","/api/providers/img/fondo/{nombreFoto:.+}","/api/providers/img/fondo/{nombreFoto:.+}","/api/providers/img/{nombreFoto:.+}","/api/pagosculqi","/api/charges","/api/proveedores","/api/publicaciones","/api/products/{id}","/api/categories","/api/proveedores","/api/proveedores/{id}","/api/publicaciones","/api/categories/{id}","/api/products","/api/products/categoria/{id}","/api/products/{id}","/api/publicaciones/{id}","/api/proveedores","/api/proveedores/{id}").permitAll()
		
		.anyRequest().authenticated();

		
	;
//		super.configure(http);
	}
	
	 

}
