package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Cliente;
import com.example.api.domain.FiltroDto;
import com.example.api.repository.ClienteRepository;

@Service
public class ClienteService {//Classe responsavel pelos serviços de persistecia referente ao cliente

	private ClienteRepository repository; //Responsável por disponibilizar o repository de perstencia na base a ser chamado
	
	@Autowired //anotação respnsavel por carregar(injetar) o bean para o seriço, neste caso injetando o repository para ser utilizado nesta classe de serviço
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public List<Cliente> findByFiltro(FiltroDto filtro) {
		return repository.findByFiltro(filtro.getNome(), filtro.getEmail(), filtro.getGenero());//Método criado para filtrar clientes por nome ou/e email ou/e genero
	}
	
	public List<Cliente> findByFiltroEndereco(FiltroDto filtro) {
		return repository.findByFiltroEndereco(filtro.getCidade(), filtro.getUf());//Busca o cliente pela cidade(localidade) ou uf referente ao seu endereço
	}
	
	public String cadastroCliente(Cliente cliente) {
		Cliente c = repository.save(cliente);
		
		if(c!=null)//verifica se a entinde Cliente foi salva. Caso retorne o objeto é dado como salvo, caso null é dando como não salvo.
			return "Salvo";
		else return null;
	}
	
	public String deletaCliente(Cliente cliente) {
		if(cliente.getId() == null)//Verifica se foi passdo o id do cliente para ser removido
			return "ID invalido para o delete";
		
		try {
			Cliente c = repository.findById(cliente.getId()).get();//busca na base pelo id o cliente atualizado a ser deletado
			
			repository.deleteById(c.getId());//deleta na base o cliente que foi buscado pelo id.
			return "deletado";
			
		}catch (Exception e) {
			return "Cliente não encontrado";//caso não encontrado
		}
		
	}
}
