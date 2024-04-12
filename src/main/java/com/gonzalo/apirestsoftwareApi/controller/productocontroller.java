package com.gonzalo.apirestsoftwareApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalo.apirestsoftwareApi.dto.productodto;
import com.gonzalo.apirestsoftwareApi.entity.mensajeresponse;
import com.gonzalo.apirestsoftwareApi.entity.producto;
import com.gonzalo.apirestsoftwareApi.service.productoservice;

import lombok.RequiredArgsConstructor;

@RestController   // se rdefine como controlador 
@RequestMapping("/producto")   // path inicial desde localhost:port/producto/
@RequiredArgsConstructor   //requeire para que se construya 
public class productocontroller {

    private final productoservice productoservicevar;

    
    @PostMapping("crear")   // se usa para crear y actualizar
    public void createperson(@RequestBody producto prod){

        productoservicevar.create(prod);   //va al servicio y a la fucnion crear
    }

      @DeleteMapping("eliminar/{id}")
    public void deleteperson(@PathVariable Integer id){

        productoservicevar.delete(id);   //va al servicio y a la fucnion crear
    }

    @GetMapping("viewall")   // se usa para crear y actualizar
    public ResponseEntity<mensajeresponse> viewall(){
      List<productodto> clienteall = productoservicevar.viewallDTO();   //va al servicio y a la fucnion crear
    
      if (clienteall == null){
            return new ResponseEntity<>(
                mensajeresponse.builder()
            .mensaje("result = null")
            .object(null)
            .build(),
             HttpStatus.OK
            );

      }
      else{
        return new ResponseEntity<>(
            mensajeresponse.builder()
            .mensaje("")
            .object(clienteall)
            .build(),
             HttpStatus.OK
            );


      }
    }


}
