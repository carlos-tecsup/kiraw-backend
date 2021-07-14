package com.kiraw.LoginKiraw.service;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraficaService {

    @Autowired
    private GraficaRepository graficaRepository;

    public List<Publication> obtener(Integer id) {
        return graficaRepository.obtener(id);
    }

}
