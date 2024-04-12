package com.gonzalo.apirestsoftwareApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalo.apirestsoftwareApi.entity.mensajeresponse;
import com.gonzalo.apirestsoftwareApi.entity.venta;
import com.gonzalo.apirestsoftwareApi.service.ventaservice;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class ventacontroller {

 private final ventaservice ventaservicevar;

 @PostMapping("crear")
 public void createventa(@RequestBody venta vent) {
    ventaservicevar.createventa(vent);
 }

 @DeleteMapping("eliminar/{id}")
 public void deleteperson(@PathVariable Integer id){

    ventaservicevar.delete(id);   //va al servicio y a la fucnion crear
 }

 @GetMapping("viewall")   // se usa para crear y actualizar
 public ResponseEntity<mensajeresponse> viewall(){
   List<venta> clienteall = ventaservicevar.viewall();   //va al servicio y a la fucnion crear
 
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
