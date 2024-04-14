package com.gonzalo.apirestsoftwareApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gonzalo.apirestsoftwareApi.dto.clientedto;
import com.gonzalo.apirestsoftwareApi.entity.cliente;
import com.gonzalo.apirestsoftwareApi.entity.producto;
import com.gonzalo.apirestsoftwareApi.entity.venta;

import jakarta.transaction.Transactional;

import java.util.List;
import java.sql.Date;
@Repository
public interface ventarepository extends JpaRepository<venta,Integer> {

    @Query("SELECT vp.producto FROM ventaproducto vp WHERE vp.venta.id = :ventaId")
    List<producto> findProductosByVentaId(@Param("ventaId") Integer ventaId);

    @Query("SELECT vp.producto FROM ventaproducto vp WHERE vp.venta.fecha >= :fechaInicio AND vp.venta.fecha <= :fechaFin")
List<producto> findProductosByFechaInicioFechaFin( @Param("fechaInicio") Date fechaInicio,  @Param("fechaFin") Date fechaFin);


@Query("SELECT SUM(vp.cantidad) FROM ventaproducto vp WHERE vp.producto.id = :prodId")
Integer findtotalByVentabyProducto(@Param("prodId") Integer prodId);


//consulta nativa
@Query(value = "SELECT SUM(vp.cantidad) FROM ventaproducto vp WHERE vp.producto_id = :prodId", nativeQuery = true)
Integer findTotalByVentaAndProductoNATIVA(@Param("prodId") Integer prodId);

@Query(value = "SELECT producto.* , SUM(ventaproducto.cantidad) as total   FROM producto , ventaproducto , venta "+
"WHERE producto.id=ventaproducto.producto_id AND ventaproducto.venta_id = venta.id AND "+
"venta.fecha >= :fechaInicio AND venta.fecha <= :fechaFin "+
"GROUP BY producto.id ORDER BY total DESC LIMIT 3 ", nativeQuery = true)
List<Object[]> findProductosWithTotalCantidad( @Param("fechaInicio") Date fechaInicio,  @Param("fechaFin") Date fechaFin);
//consutlas nativas 


@Query("SELECT cl FROM ventaproducto vp , cliente cl WHERE  vp.venta.fecha >= :fechaInicio AND vp.venta.fecha <= :fechaFin AND vp.venta.cliente.id = cl.id")
List<cliente> findClientesByFechaInicioFechaFin( @Param("fechaInicio") Date fechaInicio,  @Param("fechaFin") Date fechaFin);
    

@Query("SELECT SUM(v.total) FROM venta v WHERE v.cliente.id = :clienteId")
Double findtotalByVentabyCliente(@Param("clienteId") Integer clienteId);

}
