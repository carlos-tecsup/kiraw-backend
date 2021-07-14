package com.kiraw.LoginKiraw.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;
import com.kiraw.LoginKiraw.repository.IUsuarioDao;
import com.kiraw.LoginKiraw.service.IUsuarioService;
import com.kiraw.LoginKiraw.service.jpa.UsersService;



@Component
public class InfoAdicionalToken implements TokenEnhancer{
	@Autowired
	private IUsuarioService usuarioservice;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
	
	
			Users user=usuarioservice.findByUsername(authentication.getName());

				Map<String, Object> info = new HashMap<>();

				String str = Arrays.toString(authentication.getAuthorities().toArray());
				String r=str.replace("[","").replace("]","");
				if(str.equals("[ROLE_PROVIDER]")) {
					info.put("rol", r);
					info.put("name", authentication.getName());

					info.put("id_provider", user.getProvider().getId());
					info.put("phone", user.getProvider().getPhone());
					info.put("city", user.getProvider().getCity());
					info.put("address", user.getProvider().getAddress());
					info.put("img_background", user.getProvider().getImgBackground());
					info.put("img_profile",user.getProvider().getImgProfile());

					info.put("correo_electronico", user.getEmail());
					((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

				}
				else if(str.equals("[ROLE_CLIENT]")){
					info.put("rol", r);
					info.put("email",user.getEmail());
					info.put("name_provider", user.getClients().getName());
					info.put("id_provider", user.getClients().getId());
					info.put("username",user.getUsername());
					info.put("surname",user.getClients().getSurname());
					info.put("address",user.getClients().getAddress());
					info.put("img_profile",user.getClients().getImgProfile());
					info.put("img_background",user.getClients().getImgBackground());
					info.put("ocupation",user.getClients().getOccupation());


					info.put("correo_electronico", user.getEmail());
					((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

				}


		return accessToken;



		
	
	}
	
 
}
