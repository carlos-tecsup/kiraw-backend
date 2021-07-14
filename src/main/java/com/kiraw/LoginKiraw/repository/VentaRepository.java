package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.pagos.Venta;
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
public class VentaRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductoRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Venta> obtener(Integer id) {
        logger.info("call listar()");

        String sql = "SELECT amount,descripcion,email,token,creation_date,apellido_cliente,cantidad_producto,direccion_proveedor,id_cliente,id_proveedor,nombre_cliente,nombre_proveedor,numero_proveedor,precio_unitario  from ventas  WHERE id_cliente =? GROUP BY token";

        List<Venta> ventas = jdbcTemplate.query(sql, new RowMapper<Venta>() {
            public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
                Venta venta=new Venta();
                venta.setToken(rs.getString("token"));
                venta.setDescripcion(rs.getString("descripcion"));
                venta.setAmount(rs.getInt("amount"));
                venta.setEmail(rs.getString("email"));
                venta.setCreation_date(rs.getDate("creation_date"));
                venta.setApellido_cliente(rs.getString("apellido_cliente"));
                venta.setCantidad_producto(rs.getInt("cantidad_producto"));
                venta.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                venta.setId_cliente(rs.getInt("id_cliente"));
                venta.setId_proveedor(rs.getInt("id_proveedor"));
                venta.setNombre_cliente(rs.getString("nombre_cliente"));
                venta.setNombre_proveedor(rs.getString("nombre_proveedor"));
                venta.setNumero_proveedor(rs.getString("numero_proveedor"));
                venta.setPrecio_unitario(rs.getInt("precio_unitario"));

                return venta;
            }
        }, id);

        logger.info("productos: " + ventas);

        return ventas;
    }


    public List<Venta> obtener2(Integer id) {
        logger.info("call listar()");

        String sql = "SELECT amount,descripcion,email,token,creation_date,apellido_cliente,cantidad_producto,direccion_proveedor,id_cliente,id_proveedor,nombre_cliente,nombre_proveedor,numero_proveedor,precio_unitario  from ventas  WHERE id_proveedor=? GROUP BY token";

        List<Venta> ventas = jdbcTemplate.query(sql, new RowMapper<Venta>() {
            public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
                Venta venta=new Venta();
                venta.setToken(rs.getString("token"));
                venta.setDescripcion(rs.getString("descripcion"));
                venta.setAmount(rs.getInt("amount"));
                venta.setEmail(rs.getString("email"));
                venta.setCreation_date(rs.getDate("creation_date"));
                venta.setApellido_cliente(rs.getString("apellido_cliente"));
                venta.setCantidad_producto(rs.getInt("cantidad_producto"));
                venta.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                venta.setId_cliente(rs.getInt("id_cliente"));
                venta.setId_proveedor(rs.getInt("id_proveedor"));
                venta.setNombre_cliente(rs.getString("nombre_cliente"));
                venta.setNombre_proveedor(rs.getString("nombre_proveedor"));
                venta.setNumero_proveedor(rs.getString("numero_proveedor"));
                venta.setPrecio_unitario(rs.getInt("precio_unitario"));

                return venta;
            }
        }, id);

        logger.info("productos: " + ventas);

        return ventas;
    }
}
