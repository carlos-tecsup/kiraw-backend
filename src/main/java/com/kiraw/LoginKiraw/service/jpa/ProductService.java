package com.kiraw.LoginKiraw.service.jpa;
import com.kiraw.LoginKiraw.entity.*;
import com.kiraw.LoginKiraw.repository.ICategoryDao;
import com.kiraw.LoginKiraw.utils.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kiraw.LoginKiraw.repository.IListprovidersDao;
import com.kiraw.LoginKiraw.repository.IProductDao;
import com.kiraw.LoginKiraw.repository.IPublicationDao;
import com.kiraw.LoginKiraw.service.IProductService;
import com.kiraw.LoginKiraw.service.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService   implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private ICategoryDao categoryDao;

    public List<Product> findAll() {

        return   (List<Product>) productDao.findAll();


    }
    @Transactional()
    public List<Product> findProductByProviderId(Integer id) {
        return productDao.findProductByProviderId(id);
    }

    @Transactional()
    public List<Product> findByCategoryId(Integer id) {
        return productDao.findProductByCategoryId(id);
    }

    @Override
    @Transactional()
    public Product findById(Integer id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    @Transactional

    public Product save(Product product) {

        return productDao.save(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.deleteById(id);

    }
    @Transactional()
    public List<Product> obtener2(Integer id) {
        return productDao.findProductByCategoryId(id);
    }

    public List<Product> obtener2(Integer id,Integer id2) {
        return productDao.findProductByProviderIdAndCategoryId(id,id2);
    }

    @Override
    @Transactional()
    public List<Product> findProductByName(String term) {
        return productDao.findByNameContainingIgnoreCase(term);
    }

    @Override
    public List<Product> findProductByPriceDesc() {
        return productDao.findAllByOrderByPriceDesc();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return  productDao.findAll(pageable);
    }

    @Override
    public List<Product> findAllByOrOrderByPriceAsc() {
        return  productDao.findAllByOrderByPriceAsc();
    }



}
