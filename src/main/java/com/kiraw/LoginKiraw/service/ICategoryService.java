package com.kiraw.LoginKiraw.service;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Category;

import java.util.List;


public interface ICategoryService {
    public List<Category> findAll();
    public  List<Category> findById(Integer id);

}
