package com.generation.crudfarmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.crudfarmacia.model.CategoriaModel;
import com.generation.crudfarmacia.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaModel>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/classe/{classe}")
	public ResponseEntity<List<CategoriaModel>> getAllByClasse(@PathVariable
	String classe) {
		return ResponseEntity.ok(categoriaRepository
				.findAllByClasseContainingIgnoreCase(classe));
	}
	
	@GetMapping("/laboratorio/{laboratorio}")
	public ResponseEntity<List<CategoriaModel>> getAllByLaboratorio(@PathVariable
	String laboratorio) {
		return ResponseEntity.ok(categoriaRepository
				.findAllByLaboratorioContainingIgnoreCase(laboratorio));
	}
	
	@GetMapping("/principioAtivo/{principioAtivo}")
	public ResponseEntity<List<CategoriaModel>> getAllByPrincipioAtivo(@PathVariable
	String principioAtivo) {
		return ResponseEntity.ok(categoriaRepository
				.findAllByPrincipioAtivoContainingIgnoreCase(principioAtivo));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaModel> cadastrarCategoria(@Valid @RequestBody CategoriaModel categoria) {
		
		categoria.setId(null);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<CategoriaModel> atualizarCategoria(@Valid @RequestBody CategoriaModel categoria) {
		return categoriaRepository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<CategoriaModel> categoria = categoriaRepository.findById(id);
		
		if(categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		categoriaRepository.deleteById(id);
	}

}
