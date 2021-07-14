package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICategoryDao extends CrudRepository<Category,Integer> {
    public List<Category> findCategoriesByIdOrderByIdDesc(Integer id);
    public Category findCategoryByName(String name);
    public Category findCategoryById(Integer id);
}
