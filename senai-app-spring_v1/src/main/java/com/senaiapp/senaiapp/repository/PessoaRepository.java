package com.senaiapp.senaiapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaiapp.senaiapp.models.Pessoa;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {
    
}
