package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Category;
import com.kiraw.LoginKiraw.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kiraw.LoginKiraw.entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class ProductoRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProviderRepository.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;



    public void update(Product product) {
        logger.info("crear " + product);

        String sql = "update products set products.product_name=?,products.description=?,products.product_image=?,products.price=?,products.stock=?,products.category_id=? WHERE products.id=?";

        jdbcTemplate.update(sql,product.getName(),product.getDescription(),product.getImage(),product.getPrice(),product.getStock(),product.getCategory().getId(),product.getId());
    }

}
