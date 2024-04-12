package com.gonzalo.apirestsoftwareApi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalo.apirestsoftwareApi.service.carritoservice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class carritocontroller {

    private final carritoservice carritoservicevar;

    @PostMapping("creardetalle")   // se usa para crear y actualizar
    public void createperson(@RequestBody Map<String, List<Map<String, Object>>> payload){

        carritoservicevar.registrardetalle(payload);   //va al servicio y a la fucnion crear
    }
}
