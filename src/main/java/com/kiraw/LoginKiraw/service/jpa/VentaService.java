package com.kiraw.LoginKiraw.service.jpa;

import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.pagos.Venta;
import com.kiraw.LoginKiraw.repository.IUsuarioDao;
import com.kiraw.LoginKiraw.repository.IVentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private IVentaDao ventaDao;
    @Transactional

    public Venta save(Venta venta) {

        return ventaDao.save(venta);
    }
    /*@Transactional

    public List<Venta> findVenta(int id) {
        return ventaDao.findVentaById_cliente(id);
    }*/

}
