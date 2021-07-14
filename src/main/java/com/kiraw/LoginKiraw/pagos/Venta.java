package com.kiraw.LoginKiraw.pagos;

import com.culqi.Culqi;
import com.culqi.util.CurrencyCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="ventas")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venta {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @JsonProperty
    @Column
    private Integer amount ;

    @JsonProperty
    @Column
    private String token;

    @JsonProperty
    @Column
    private String email ;

    @JsonProperty
    @Column
    private String nombre_cliente;
    @JsonProperty
    @Column
    private String descripcion;

    @JsonProperty
    @Column
    private String nombre_proveedor;

    @JsonProperty
    @Column
    private String numero_proveedor;

    @JsonProperty
    @Column
    private String direccion_proveedor;


    @JsonProperty
    @Column
    private String apellido_cliente;

    @JsonProperty
    @Column
    private Integer id_cliente;

    @JsonProperty
    @Column
    private Integer id_proveedor;


    @JsonProperty
    @Column
    private Integer cantidad_producto;

    @JsonProperty
    @Column
    private Integer precio_unitario;



    @CreationTimestamp
    @Column
    private Date creation_date;




    @PrePersist
    public void prePersist() {
        creation_date=new Date();
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getNumero_proveedor() {
        return numero_proveedor;
    }

    public void setNumero_proveedor(String numero_proveedor) {
        this.numero_proveedor = numero_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Integer getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(Integer cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public Integer getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Integer precio_unitario) {
        this.precio_unitario = precio_unitario;
    }




    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
