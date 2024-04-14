package com.gonzalo.apirestsoftwareApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonzalo.apirestsoftwareApi.entity.ventaproducto;

@Repository
public interface ventaproductorepository extends JpaRepository<ventaproducto,Integer> {

}

// para las consultas , asi se estructura la tabla intermedia 
/* {
    "mensaje": "",
    "object": [
        {
            "id": 552,
            "venta": {
                "id": 1152,
                "fecha": "2024-04-12",
                "ventaProductos": []
            },
            "producto": {
                "id": 302,
                "nombre": "pan",
                "stock": 12,
                "costo": 2.5,
                "precio": 3.0,
                "ventaProductos": []
            },
            "cantidad": 3,
            "preciototal": 9.0
        },
        {
            "id": 553,
            "venta": {
                "id": 1152,
                "fecha": "2024-04-12",
                "ventaProductos": []
            },
            "producto": {
                "id": 303,
                "nombre": "cafe",
                "stock": 13,
                "costo": 5.0,
                "precio": 6.7,
                "ventaProductos": []
            },
            "cantidad": 6,
            "preciototal": 40.2
        }
    ]
} */