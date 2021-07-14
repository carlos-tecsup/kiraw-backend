package com.kiraw.LoginKiraw.service.jpa;

import com.kiraw.LoginKiraw.entity.Category;
import com.kiraw.LoginKiraw.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listar(){
        return categoryRepository.listar();
    }
    public List<Category> obtener(Integer id) {
        return categoryRepository.obtener(id);
    }


}
