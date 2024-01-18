package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Endereco;
import com.example.api.service.EnderecoService;

@CrossOrigin("*")//possibilita qualquer aplicação externa a se comunicar com a API de Endereço
@RestController
@RequestMapping("/endereco-api")
public class EnderecoController {
	@Autowired
	private EnderecoService enderecoService;
	
	@Description(value = "API retorna todos os endereços")
	@GetMapping("/enderecos")
	public List<Endereco> findAllEndereco() {
		return enderecoService.findAll();
	}

	@Description(value = "API responsavel pelo cadastro de endereço referente a um Cliente")
	@PostMapping("/cadastro-endereco")
	public String cadastroEndereco(@RequestBody Endereco endereco) {
		return enderecoService.cadastroEndereco(endereco);
	}
	
	@Description(value = "API para buscar um endereço através do CEP")
	@GetMapping("/endereco-cep")
	public String findEnderecoByCep(@RequestParam(name = "cep") String cep) {
		return enderecoService.findEnderecoByCep(cep);
	}
}
