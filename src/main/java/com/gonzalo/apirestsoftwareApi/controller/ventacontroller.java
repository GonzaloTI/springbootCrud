package com.gonzalo.apirestsoftwareApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalo.apirestsoftwareApi.dto.clientedto;
import com.gonzalo.apirestsoftwareApi.dto.productodto;
import com.gonzalo.apirestsoftwareApi.entity.mensajeresponse;
import com.gonzalo.apirestsoftwareApi.entity.venta;
import com.gonzalo.apirestsoftwareApi.entity.ventaproducto;
import com.gonzalo.apirestsoftwareApi.service.ventaservice;

import lombok.RequiredArgsConstructor;
import java.text.ParseException;
import java.util.Map;

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
 
 @GetMapping("viewallventaproducto")   // se usa para crear y actualizar
 public ResponseEntity<mensajeresponse> viewallventaprodcuto() throws ParseException{

   List<ventaproducto> clienteall = ventaservicevar.viewallVENTAPRODUCTO();   //va al servicio y a la fucnion crear
 
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


 @PostMapping("consulta1")   // se usa para crear y actualizar
 public ResponseEntity<mensajeresponse> consulta1(@RequestBody Map<String, Object> requestBody  ) throws ParseException{
   String fechainicio = (String) requestBody.get("fechainicio");
   String fechafin = (String) requestBody.get("fechafin");
   
   List<Object>  response = ventaservicevar.clientequemascompro(fechainicio, fechafin);

     if (response == null){
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
       .object(response)
       .build(),
        HttpStatus.OK
       );


 }
 }

 @PostMapping("consulta2")   // se usa para crear y actualizar
 public ResponseEntity<mensajeresponse> consulta2(@RequestBody Map<String, Object> requestBody  ) throws ParseException{
   String fechainicio = (String) requestBody.get("fechainicio");
   String fechafin = (String) requestBody.get("fechafin");
   
   List<productodto> response = ventaservicevar.productosquemassecompraron(fechainicio, fechafin);

     if (response == null){
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
       .object(response)
       .build(),
        HttpStatus.OK
       );


 }
 }


}
