package com.gonzalo.apirestsoftwareApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gonzalo.apirestsoftwareApi.entity.venta;
import com.gonzalo.apirestsoftwareApi.repository.ventarepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ventaservice {
    private final ventarepository vetarepo;

    public void createventa( venta clientenew){
        vetarepo.save(clientenew);
    }

    public venta findbyid(Integer id){
        return vetarepo.findById(id).orElse(null);

   }

   public void delete(Integer id){
       venta clientefind = vetarepo.findById(id).orElse(null);
       vetarepo.delete(clientefind);
   }


   public List<venta> viewall(){
       return vetarepo.findAll();
   }





}
