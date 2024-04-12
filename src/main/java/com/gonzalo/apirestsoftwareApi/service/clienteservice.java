package com.gonzalo.apirestsoftwareApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gonzalo.apirestsoftwareApi.dto.clientedto;
import com.gonzalo.apirestsoftwareApi.entity.cliente;
import com.gonzalo.apirestsoftwareApi.repository.clienterepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class clienteservice {

    private final clienterepository clienterepo;

    public void createpersona( cliente clientenew){
        clienterepo.save(clientenew);


    }

    public cliente findbyid(Integer id){
        return clienterepo.findById(id).orElse(null);

   }

   public void delete(Integer id){
       cliente clientefind = clienterepo.findById(id).orElse(null);
       clienterepo.delete(clientefind);
   }


   public List<cliente> viewall(){
       return clienterepo.findAll();


   }


   public List<clientedto> viewallDTO(){
    List<cliente> clientes = clienterepo.findAll();
    // Convertir la lista de entidades Cliente a una lista de DTOs ClienteDTO utilizando ModelMapper
    return convertirClientesADTOs(clientes);
}

private List<clientedto> convertirClientesADTOs(List<cliente> clientes) {
    ModelMapper modelMapper = new ModelMapper();
    return clientes.stream()
                  .map(cliente -> modelMapper.map(cliente, clientedto.class))
                  .collect(Collectors.toList());
}

public clientedto validar(String email,Integer nit){
    cliente cliente = clienterepo.findByEmail(email) ;
    if(cliente.getNit().equals(nit)){
     System.out.println(nit);
    System.out.println(cliente.getNit());
     clientedto clienteDTO = convertirClienteADTO(cliente);

   return clienteDTO;
    }
    else {return null;}
  
 
}
private clientedto convertirClienteADTO(cliente cliente) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(cliente, clientedto.class);
}



}
