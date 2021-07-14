package com.kiraw.LoginKiraw.controller;


import com.kiraw.LoginKiraw.entity.Category;
import com.kiraw.LoginKiraw.service.ICategoryService;
import com.kiraw.LoginKiraw.service.jpa.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ICategoryService categoryService;

    @Secured({"ROLE_PROVIDER","ROLE_CLIENT"})
    @GetMapping("/categories")
    public List<Category> categories() {


        List<Category> categories = categoriaService.listar();

        return categories;
    }
    @Secured({"ROLE_PROVIDER","ROLE_CLIENT"})
    @GetMapping("/categories/{id}")
    public List<Category> categories(@PathVariable Integer id){
      return categoryService.findById(id);

    }

    @Secured({"ROLE_PROVIDER","ROLE_CLIENT"})

    @GetMapping("/categorias/provider/{id}")
    public List<Category> obtener(@PathVariable Integer id) {

        List<Category> category = categoriaService.obtener(id);

        return category;
    }

}
