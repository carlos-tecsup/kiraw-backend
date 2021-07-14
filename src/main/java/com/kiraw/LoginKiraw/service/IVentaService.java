package com.kiraw.LoginKiraw.service;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.pagos.Venta;

import java.util.List;

public interface IVentaService {
    public Venta save(Venta venta);
    public List<Venta> findAll();

}
