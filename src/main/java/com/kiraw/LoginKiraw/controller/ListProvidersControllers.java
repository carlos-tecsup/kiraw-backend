package com.kiraw.LoginKiraw.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.service.IListproviderService;
import com.kiraw.LoginKiraw.service.IPublicationService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class ListProvidersControllers {

    @Autowired
    private IListproviderService iListproviderService;

    //@Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @GetMapping("/proveedores")
    public List<Provider> index() {

        return iListproviderService.findAll();
    }

}
