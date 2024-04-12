package com.gonzalo.apirestsoftwareApi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data      // para los getter y setter
@AllArgsConstructor   // constructor para que reciba parametros
@NoArgsConstructor  // constructor  que no reciba parametros
@ToString
@Builder
public class clientedto {
  
    private Integer id;
    private String nombre;
    private String email;
}
