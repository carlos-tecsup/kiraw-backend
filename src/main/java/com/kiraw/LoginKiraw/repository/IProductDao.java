package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

public interface IProductDao extends CrudRepository<Product, Integer> {
        @Query("select p from Product p where p.name like %?1%")
        public  List<Product> findByName(String term);

    public  List<Product> findByNameContainingIgnoreCase(String term);
    public List<Product> findAllByOrderByPriceAsc();
    public List<Product> findAllByOrderByPriceDesc();
    public Page<Product> findAll(Pageable pageable);
    public List<Product> findProductByProviderId(Integer id);
    public List<Product> findProductByCategoryId(Integer id);
    public List<Product> findProductByProviderIdAndCategoryId(Integer d,Integer id);

}
