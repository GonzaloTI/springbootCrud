package com.gonzalo.apirestsoftwareApi.entity;


import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ventaproducto {

    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private producto producto;

    @Basic
    private Integer cantidad;
    private Double preciototal;

}
