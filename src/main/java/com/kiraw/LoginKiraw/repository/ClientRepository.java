package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Clients;
import com.kiraw.LoginKiraw.entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProviderRepository.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;



    public void crear(Clients clients) {
        logger.info("crear " + clients);

        String sql = "update clients set img_profile=?,img_background=?,address=?,name=?,occupation=?,phone=?,surname=? where id=?";

        jdbcTemplate.update(sql, clients.getImgProfile(),clients.getImgBackground(),clients.getAddress(),clients.getName(),clients.getOccupation(),clients.getPhone(),clients.getSurname(),clients.getId());
    }

    public void crearimg(Clients clients) {
        logger.info("crear " + clients);

        String sql = "update clients set img_background=? where id=?";

        jdbcTemplate.update(sql,  clients.getImgProfile(),clients.getId());
    }
}
