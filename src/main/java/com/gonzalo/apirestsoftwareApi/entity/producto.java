package com.gonzalo.apirestsoftwareApi.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class producto {
    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    private String nombre;
    private Integer stock;
    private Double costo;
    private Double precio;
    @OneToMany(mappedBy = "producto")
private Set<ventaproducto> ventaProductos = new HashSet<>();
}
