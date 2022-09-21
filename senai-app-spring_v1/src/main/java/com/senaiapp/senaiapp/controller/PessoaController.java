package com.senaiapp.senaiapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaiapp.senaiapp.models.Pessoa;
import com.senaiapp.senaiapp.repository.PessoaRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge =3600)
@RestController
@RequestMapping({"/pessoas"})
public class PessoaController {
  
    private PessoaRepository repository;

    PessoaController(PessoaRepository contactRepository) {
        this.repository = contactRepository;
    }
  
    // CRUD methods here
    //pega todos os contatos do banco e retorna uma lista
    @GetMapping
  public List<Pessoa> findAll(){
    return repository.findAll();
  }
  
  //pega um contato pelo seu ID
  @GetMapping(path = {"/{id}"})
  public ResponseEntity<Pessoa> findById(@PathVariable Integer id){
    return repository.
    findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
  }
  
  //método para salvar o contato no banco
  @PostMapping
  public Pessoa create(@RequestBody Pessoa p){
      return repository.save(p);
  }
  
  //método para atualizar um contato
  @PutMapping(value="/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable("id") Integer id,
                                          @RequestBody Pessoa p){
      return repository.findById(id)
          .map(record -> {
              record.setId(p.getId());
              record.setNome(p.getNome());
              record.setApelido(p.getApelido());
              Pessoa updated = repository.save(record);
              return ResponseEntity.ok().body(updated);
          }).orElse(ResponseEntity.notFound().build());
    }
  
  //método para deletar um contato
  @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
      return repository.findById(id)
          .map(record -> {
              repository.deleteById(id);
              return ResponseEntity.ok().build();
          }).orElse(ResponseEntity.notFound().build());
    }
    

}
