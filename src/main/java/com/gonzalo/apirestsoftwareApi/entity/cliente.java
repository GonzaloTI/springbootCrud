package com.gonzalo.apirestsoftwareApi.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data      // para los getter y setter
@AllArgsConstructor   // constructor para que reciba parametros
@NoArgsConstructor  // constructor  que no reciba parametros
@Entity            // esta es un entidad
public class cliente {
    @Id          // se va a mapear para los identificadores
    @GeneratedValue// que se genere automaticamente genereciton type automatic
    private Integer id;
    @Basic
    private String nombre;
    private Integer nit;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<venta> ventas;
}
