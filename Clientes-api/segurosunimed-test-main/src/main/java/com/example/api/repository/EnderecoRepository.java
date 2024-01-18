package com.example.api.repository;

import com.example.api.domain.Endereco;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

	List<Endereco> findAll();
}
