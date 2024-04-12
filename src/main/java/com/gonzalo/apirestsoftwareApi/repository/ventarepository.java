package com.gonzalo.apirestsoftwareApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonzalo.apirestsoftwareApi.entity.venta;

@Repository
public interface ventarepository extends JpaRepository<venta,Integer> {

}
