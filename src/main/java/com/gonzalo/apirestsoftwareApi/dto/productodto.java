package com.gonzalo.apirestsoftwareApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class productodto {
 
    private Integer id;
    private String nombre;
    private Integer stock;
    private Double precio;
 
}
