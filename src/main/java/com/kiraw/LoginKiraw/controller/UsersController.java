package com.kiraw.LoginKiraw.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;
import com.kiraw.LoginKiraw.service.IUsersService;


@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private IUsersService serviceUsers;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/users/{id}")
    public Users show(@PathVariable int id) {


        return serviceUsers.findProviderByUsers(id);


    }

}
