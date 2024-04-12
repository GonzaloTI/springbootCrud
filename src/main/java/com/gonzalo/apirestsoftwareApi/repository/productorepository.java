package com.gonzalo.apirestsoftwareApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonzalo.apirestsoftwareApi.entity.producto;

@Repository
public interface productorepository extends JpaRepository<producto,Integer> {

}
