package com.gonzalo.apirestsoftwareApi.entity;



import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class venta {
    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;
    private Double total;

    @OneToMany(mappedBy = "venta")
private Set<ventaproducto> ventaProductos = new HashSet<>();

  
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private cliente cliente;

}
