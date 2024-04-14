package com.gonzalo.apirestsoftwareApi.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.gonzalo.apirestsoftwareApi.dto.clientedto;
import com.gonzalo.apirestsoftwareApi.dto.productodto;
import com.gonzalo.apirestsoftwareApi.entity.cliente;
import com.gonzalo.apirestsoftwareApi.entity.producto;
import com.gonzalo.apirestsoftwareApi.entity.venta;
import com.gonzalo.apirestsoftwareApi.entity.ventaproducto;
import com.gonzalo.apirestsoftwareApi.repository.ventaproductorepository;
import com.gonzalo.apirestsoftwareApi.repository.ventarepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ventaservice {
    private final ventarepository vetarepo;
private final ventaproductorepository ventaprodrepo;


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
        List<producto> findprod =vetarepo.findProductosByVentaId(1152);
        for (producto prod : findprod) {
         System.out.println(prod.getId());
         System.out.println(prod.getNombre() + prod.getPrecio());
      }
       return vetarepo.findAll();
   }

   public List<ventaproducto> viewallVENTAPRODUCTO() throws ParseException{
 // Formato de fecha esperado
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

 Double maxventa =0.0;
 clientedto clientepremiun = new clientedto() ;

 java.util.Date utilDate = sdf.parse("2024-04-10");

 java.util.Date utilDate2 = sdf.parse("2024-04-30");

 Date fecha = new Date(utilDate.getTime());
 Date fecha2 = new Date(utilDate2.getTime());

    List<clientedto> findclient =   convertirClientesADTOs( vetarepo.findClientesByFechaInicioFechaFin(  fecha,fecha2));
        for (clientedto prod : findclient) {
         System.out.println(prod.getId());
         System.out.println(prod.getNombre());
         Double totalbycliente = vetarepo.findtotalByVentabyCliente(prod.getId()); // total de venta por clientes 
         System.out.println(totalbycliente.toString());

        if(totalbycliente >= maxventa){
            clientepremiun=prod;
        }

      }

      System.out.println(clientepremiun.toString());
    

    return    ventaprodrepo.findAll();
}


@Transactional
public List<Object>  clientequemascompro(String inicio , String fin) throws ParseException{
// Formato de fecha esperado
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
java.util.Date utilDate = sdf.parse(inicio);

java.util.Date utilDate2 = sdf.parse(fin);

Date fecha = new Date(utilDate.getTime());
Date fecha2 = new Date(utilDate2.getTime());

Double maxventa =0.0;
 clientedto clientepremiun = new clientedto() ;

   List<clientedto> findprod = convertirClientesADTOs( vetarepo.findClientesByFechaInicioFechaFin(  fecha,fecha2));
       for (clientedto client : findprod) {
        System.out.println(client.getId());
        System.out.println(client.getNombre());
        Double totalbycliente = vetarepo.findtotalByVentabyCliente(client.getId()); // total $$ en venta por clientes 
        System.out.println(totalbycliente.toString());
     
     if(totalbycliente >= maxventa){
        maxventa=totalbycliente;
        clientepremiun=client;
    }
        }

        List<Object> res = new ArrayList<>();
        Map<String, Object> datos = new HashMap<>();
        // Agregar etiqueta y valor al Map
        datos.put("total", maxventa.toString());
        res.add(clientepremiun);

        res.add(datos);
        return res;
    
};

@Transactional
public List<productodto> productosquemassecompraron(String inicio , String fin) throws ParseException{
// Formato de fecha esperado
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
java.util.Date utilDate = sdf.parse(inicio);

java.util.Date utilDate2 = sdf.parse(fin);

Date fecha = new Date(utilDate.getTime());
Date fecha2 = new Date(utilDate2.getTime());

Integer maxventa =0;
productodto prodmas = new productodto() ;
/* 
   List<productodto> findprod = convertirproductosaDTO( vetarepo.findProductosByFechaInicioFechaFin(  fecha,fecha2));

       for (productodto client : findprod) {
        System.out.println(client.getId());
        System.out.println(client.getNombre());
        Integer totalbycliente = vetarepo.findtotalByVentabyProducto(client.getId()); // total $$ en venta por producto 
        System.out.println(totalbycliente.toString());
     
     if(totalbycliente > maxventa){
        maxventa=totalbycliente;
        prodmas=client;
    }
        }

 List<Object> res = new ArrayList<>();
  res.add(prodmas);

  Map<String, Object> datos = new HashMap<>();
        // Agregar etiqueta y valor al Map
        datos.put("total", maxventa.toString());
     
res.add(datos);

*/

  

   List<Object[]> resultados = vetarepo.findProductosWithTotalCantidad(fecha,fecha2);
        System.out.println(resultados.toString());

     

        List<productodto> listRes = new ArrayList<productodto>();


   for (Object[] resultado : resultados) {
    productodto prods = new productodto();
    prods.setId(Integer.parseInt( resultado[0].toString()));
    prods.setNombre(resultado[2].toString());
    prods.setStock(Integer.parseInt( resultado[5].toString()));
       // Aquí puedes trabajar con el Producto y el total de la cantidad
       System.out.println(prods.toString());
       listRes.add(prods);
   }
return listRes;
    
};

private List<clientedto> convertirClientesADTOs(List<cliente> clientes) {
    ModelMapper modelMapper = new ModelMapper();
    return clientes.stream()
                  .map(cliente -> modelMapper.map(cliente, clientedto.class))
                  .collect(Collectors.toList());
}
private List<productodto> convertirproductosaDTO(List<producto> productos) {
    ModelMapper modelMapper = new ModelMapper();
    return productos.stream()
                  .map(producto -> modelMapper.map(producto, productodto.class))
                  .collect(Collectors.toList());
}
}
