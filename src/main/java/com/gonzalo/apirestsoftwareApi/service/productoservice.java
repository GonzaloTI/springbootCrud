package com.gonzalo.apirestsoftwareApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gonzalo.apirestsoftwareApi.dto.productodto;
import com.gonzalo.apirestsoftwareApi.entity.producto;
import com.gonzalo.apirestsoftwareApi.repository.productorepository;

import lombok.RequiredArgsConstructor;

@Service   //se define como servicio
@RequiredArgsConstructor   //se requiere todos los constructores
public class productoservice {

    private final productorepository productorepo;

    public void create( producto clientenew){
        productorepo.save(clientenew);


    }

    public producto findbyid(Integer id){
        return productorepo.findById(id).orElse(null);

   }

   public void delete(Integer id){
       producto clientefind = productorepo.findById(id).orElse(null);
       productorepo.delete(clientefind);
   }


   public List<producto> viewall(){
       return productorepo.findAll();
   }

   public List<productodto> viewallDTO(){
    List<producto> productos = productorepo.findAll();
    // Convertir la lista de entidades productos a una lista de DTOs productosDTO utilizando ModelMapper
    return convertirproductosaDTO(productos);
}

private List<productodto> convertirproductosaDTO(List<producto> productos) {
    ModelMapper modelMapper = new ModelMapper();
    return productos.stream()
                  .map(producto -> modelMapper.map(producto, productodto.class))
                  .collect(Collectors.toList());
}
}
