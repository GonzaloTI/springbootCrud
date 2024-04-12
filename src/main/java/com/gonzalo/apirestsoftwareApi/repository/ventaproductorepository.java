package com.gonzalo.apirestsoftwareApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonzalo.apirestsoftwareApi.entity.ventaproducto;

@Repository
public interface ventaproductorepository extends JpaRepository<ventaproducto,Integer> {

}
