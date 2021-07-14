package com.kiraw.LoginKiraw.controller;

import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.service.GraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class GraficaController {

    @Autowired
    private GraficaService graficaService;

    @Secured({"ROLE_PROVIDER","ROLE_CLIENT"})

    @GetMapping("/graficos/{id}")
    public List<Publication> obtener(@PathVariable Integer id) {

        List<Publication> publication = graficaService.obtener(id);

        return publication;
    }


}
