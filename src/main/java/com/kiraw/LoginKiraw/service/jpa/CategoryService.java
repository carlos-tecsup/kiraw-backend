package com.kiraw.LoginKiraw.service.jpa;

import com.kiraw.LoginKiraw.entity.Category;
import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.repository.ICategoryDao;
import com.kiraw.LoginKiraw.repository.IProductDao;
import com.kiraw.LoginKiraw.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CategoryService  implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;


    public List<Category> findAll() {

        return   (List<Category>) categoryDao.findAll();


    }

    @Transactional()
    public List<Category> findById(Integer id){
        return categoryDao.findCategoriesByIdOrderByIdDesc(id);
    }
}
