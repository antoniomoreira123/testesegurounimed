package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.api.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	List<Cliente> findAll();

	@Query("select c from Cliente c where c.nome=?1 OR c.email=?2 OR c.genero=?3")//O cliente é buscado através dos atributos nome ou email ou genero, poderia ser um END, porém assim com OR fica mais generico
	List<Cliente> findByFiltro(String mome, String email, String genero);

	@Query("select c from Cliente c join Endereco e on e.cliente.id=c.id where e.localidade=?1 OR e.uf=?2")//os clientes são buscados conforme o seu endereço associado
	List<Cliente> findByFiltroEndereco(String cidade, String uf);
}
