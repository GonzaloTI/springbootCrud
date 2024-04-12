package com.gonzalo.apirestsoftwareApi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalo.apirestsoftwareApi.dto.clientedto;
import com.gonzalo.apirestsoftwareApi.entity.cliente;
import com.gonzalo.apirestsoftwareApi.entity.mensajeresponse;
import com.gonzalo.apirestsoftwareApi.service.clienteservice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class clientecontroller {

    private final clienteservice clienteservicevar;

    @PostMapping("crear")   // se usa para crear y actualizar
    public void createperson(@RequestBody cliente client){

        clienteservicevar.createpersona(client);   //va al servicio y a la fucnion crear
    }

      @DeleteMapping("eliminar/{id}")
    public void deleteperson(@PathVariable Integer id){

        clienteservicevar.delete(id);   //va al servicio y a la fucnion crear
    }

    @GetMapping("viewall")   // se usa para crear y actualizar
    public ResponseEntity<mensajeresponse> viewall(){

      List<cliente> clienteall = clienteservicevar.viewall();   //va al servicio y a la fucnion crear
    
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

    @PostMapping("login")   // se usa para crear y actualizar
    public ResponseEntity<mensajeresponse> validarcliente(@RequestBody Map<String, Object> requestBody  ){
      String email = (String) requestBody.get("email");
      Integer nit = (Integer) requestBody.get("nit");
        clientedto response = clienteservicevar.validar(email,nit);   //va al servicio y a la fucnion crear

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
