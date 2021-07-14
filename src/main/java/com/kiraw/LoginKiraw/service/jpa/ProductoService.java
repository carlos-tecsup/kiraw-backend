package com.kiraw.LoginKiraw.service.jpa;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.GraficaRepository;
import com.kiraw.LoginKiraw.repository.ProductoRepository;
import com.kiraw.LoginKiraw.repository.ProductsByProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;


    public void actualizar(Product product) {
        productoRepository.update(product);
    }
    @Autowired
    private ProductsByProviderRepository productsByProviderRepository;

    public List<Product> obtener(Integer id) {
        return productsByProviderRepository.obtener(id);
    }



}
