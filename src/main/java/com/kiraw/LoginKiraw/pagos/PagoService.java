package com.kiraw.LoginKiraw.pagos;

import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.GraficaRepository;
import com.kiraw.LoginKiraw.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {
    @Autowired
    private VentaRepository ventaRepository;



    public List<Venta> obtener(Integer id) {
        return ventaRepository.obtener(id);
    }

    public List<Venta> obtener2(Integer id) {
        return ventaRepository.obtener2(id);
    }

}
