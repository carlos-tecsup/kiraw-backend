package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductsByProviderRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductoRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> obtener(Integer id) {
        logger.info("call listar()");

        String sql = "SELECT products.provider_id,products.id,products.product_name,products.price,products.stock,products.product_image,products.description,products.date_created FROM products WHERE products.provider_id=? GROUP BY(products.product_name)";

        List<Product> products = jdbcTemplate.query(sql, new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                Product product = new Product();
                product.setProvider(provider);
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("product_image"));
                product.setDescription(rs.getString("description"));
                product.setDate_created(rs.getDate("date_created"));
                return product;
            }
        }, id);

        logger.info("productos: " + products);

        return products;
    }
}