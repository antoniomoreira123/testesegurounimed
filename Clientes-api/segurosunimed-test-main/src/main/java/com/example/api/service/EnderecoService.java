package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Endereco;
import com.example.api.repository.EnderecoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	public List<Endereco> findAll() {
		return repository.findAll();//recupera todos os endereços
	}
	
	public String cadastroEndereco(Endereco endereco) {
		ObjectMapper ob = new ObjectMapper();
		String json = findEnderecoByCep(String.valueOf(endereco.getCep()));
		try {
			Endereco enderecoJsonObj = ob.readValue(json, Endereco.class);//Mapeia o objeto JSON para o Objeto entidade Endereco, tornando automático esse cast
			enderecoJsonObj.setCliente(endereco.getCliente());//adiciona no endereço o cliente passado no filtro
			Endereco c = repository.save(enderecoJsonObj);//salva o endereço e retorna este já salvo
			if(c!=null)
				return "Salvo";
			else return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}
	
	public String findEnderecoByCep(String cep) {//método para buscar o endereço através do CEP que o usuário insere
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> s =  restTemplate.getForEntity("https://viacep.com.br/ws/"+cep+"/json/",String.class);
		System.out.print(s);
		 return s.getBody();//retorna o JSON da API, contendo as informações do endereço
	}
	
}
