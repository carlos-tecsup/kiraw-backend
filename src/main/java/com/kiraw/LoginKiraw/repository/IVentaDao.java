package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Users;
import com.kiraw.LoginKiraw.pagos.Venta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IVentaDao  extends CrudRepository<Venta, Integer> {
}
