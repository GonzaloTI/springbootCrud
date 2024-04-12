package com.gonzalo.apirestsoftwareApi.service;


import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.sql.Date;
import org.springframework.stereotype.Service;

import com.gonzalo.apirestsoftwareApi.entity.cliente;
import com.gonzalo.apirestsoftwareApi.entity.producto;
import com.gonzalo.apirestsoftwareApi.entity.venta;
import com.gonzalo.apirestsoftwareApi.entity.ventaproducto;
import com.gonzalo.apirestsoftwareApi.repository.clienterepository;
import com.gonzalo.apirestsoftwareApi.repository.productorepository;
import com.gonzalo.apirestsoftwareApi.repository.ventaproductorepository;
import com.gonzalo.apirestsoftwareApi.repository.ventarepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class carritoservice {

    private final ventarepository ventarepo;
    private final productorepository productorepo;

    private final ventaproductorepository ventaproductodetalle;

    private final clienterepository clienterepo;

    public void registrardetalle(  Map<String, List<Map<String, Object>>> payload ){
        List<Map<String, Object>> carrito = payload.get("carrito");
        List<Map<String, Object>> user = payload.get("user");
        Map<String, Object> userObject = user.get(0); // Obtener el primer objeto de la lista
        Integer userId = (Integer) userObject.get("user"); 
        System.out.println( "user id :" +  userId);
      

        cliente cliente = clienterepo.findById(userId).orElse(null);


        venta venta = new venta();
        venta.setCliente(cliente);
       venta.setFecha(fechaactual());
        ventarepo.save(venta);// se crea la venta para insertarlos detalles

        for (Map<String, Object> item : carrito) {
            Integer id = (Integer) item.get("id"); // id del producto
          //  Double precio = (Double) item.get("precio");
            Double precio = ((Number) item.get("precio")).doubleValue();
            Integer cantidad =(Integer) item.get("cantidad");
            producto productoAuxiliar = productorepo.findById(id).orElse(null);

            if(productoAuxiliar !=null){
                ventaproducto ventaProductoAuxiliar = new ventaproducto();
                ventaProductoAuxiliar.setVenta(venta);
                ventaProductoAuxiliar.setProducto(productoAuxiliar); 
                ventaProductoAuxiliar.setCantidad(cantidad);
                Double total = precio *cantidad;
                ventaProductoAuxiliar.setPreciototal(total);
                 ventaproductodetalle.save(ventaProductoAuxiliar);
            }

          
            
            String nombre = (String) item.get("nombre");
           

            // Realiza las operaciones necesarias con estos datos
            System.out.println("ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio);
        }


    }

    public Date fechaactual() {
        LocalDate fechaActual = LocalDate.now();
        // Convertir la fecha actual a java.sql.Date
        Date fechaSQL = Date.valueOf(fechaActual);
        // Guardar la fecha en tu entidad o realizar la acción que necesites
       return fechaSQL;
    }

}
