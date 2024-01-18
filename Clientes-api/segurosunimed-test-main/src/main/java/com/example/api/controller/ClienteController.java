package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Cliente;
import com.example.api.domain.Endereco;
import com.example.api.domain.FiltroDto;
import com.example.api.service.ClienteService;
import com.example.api.service.EnderecoService;

@CrossOrigin("*")//possibilita qualquer aplicação externa a se comunicar com a API de Clientes
@RestController
@RequestMapping("/clientes-api")
public class ClienteController {

	private ClienteService service;


	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@Description(value = "Retorna uma lista de todos os Clientes")
	@GetMapping
	public List<Cliente> findAll() {
		return service.findAll();
	}

	@Description(value = "Retorna uma lista de Clientes conforme o filtro por nome, email e genero")
	@GetMapping("/filtro")
	public List<Cliente> findByFiltros(@RequestBody FiltroDto filtro) {
		return service.findByFiltro(filtro);
	}

	@Description(value = "API para salvar ou atualizar novo Cliente")
	@PostMapping("/cadastro-update")
	public String cadastroCliente(@RequestBody Cliente cliente) {
		return service.cadastroCliente(cliente);
	}
	
	@Description(value = "API para deletar um Cliente pelo ID")
	@DeleteMapping("/delete")
	public String deletaCliente(@RequestBody Cliente cliente) {
		return service.deletaCliente(cliente);
	}

	@Description(value = "API que lista Clientes conforme o a cidade ou UF associado a este")
	@GetMapping("/filtro-endereco")
	public List<Cliente> findByFiltrosEndereco(@RequestBody FiltroDto filtro) {
		return service.findByFiltroEndereco(filtro);
	}
}
