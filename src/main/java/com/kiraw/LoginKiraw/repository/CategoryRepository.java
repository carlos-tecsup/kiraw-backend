package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> listar(){

        String sql = "SELECT name,id FROM categories GROUP BY(name)";

        List<Category> productos = jdbcTemplate.query(sql, new RowMapper<Category>() {
            public Category  mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                return category;
            }
        });


        return productos;
    }




    public List<Category> obtener(Integer id){

        String sql = "SELECT categories.name,categories.id FROM categories INNER JOIN products WHERE categories.id=products.category_id AND PRODUCTS.provider_id=?";

        List<Category> categories= jdbcTemplate.query(sql, new RowMapper<Category>() {
            public Category  mapRow(ResultSet rs, int rowNum) throws SQLException {
                Provider provider=new Provider();
                provider.setId(id);

                Category category1 = new Category();
                category1.setId(rs.getInt("id"));
                category1.setName(rs.getString("name"));
                Product product=new Product();
                product.setProvider(provider);
                product.setCategory(category1);
                return category1;
            }
        },id);


        return categories;
    }

}
