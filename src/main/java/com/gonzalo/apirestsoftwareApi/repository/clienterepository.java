package com.gonzalo.apirestsoftwareApi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonzalo.apirestsoftwareApi.entity.cliente;

@Repository
public interface clienterepository extends JpaRepository<cliente , Integer>{
    cliente findByEmail(String email);
}
