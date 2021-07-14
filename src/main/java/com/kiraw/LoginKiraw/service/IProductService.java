package com.kiraw.LoginKiraw.service;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product findById(Integer id);
    public Product save(Product product);
    public void delete(Integer id);
    public List<Product> findProductByName(String term);
    public List<Product> findProductByPriceDesc();
    public Page<Product> findAll(Pageable pageable);
    public List<Product>findAllByOrOrderByPriceAsc();
}
