package br.com.dti.springbootapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.dti.springbootapi.entity.Produto;
import br.com.dti.springbootapi.repository.ProdutoRepository;

@RestController
public class ProdutoController {
    @Autowired
    private ProdutoRepository _ProdutoRepository;

    @RequestMapping(value = "/produto", method = RequestMethod.GET)
    public List<Produto> Get() {
        return _ProdutoRepository.findAll();
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Produto> Produto = _ProdutoRepository.findById(id);
        if(Produto.isPresent())
            return new ResponseEntity<Produto>(Produto.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/produto", method =  RequestMethod.POST)
    public Produto Post(@Valid @RequestBody Produto Produto)
    {
        return _ProdutoRepository.save(Produto);
    }

    @RequestMapping(value = "/produto/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Produto> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Produto newProduto)
    {
        Optional<Produto> oldProduto = _ProdutoRepository.findById(id);
        if(oldProduto.isPresent()){
            Produto Produto = oldProduto.get();
            Produto.setNome(newProduto.getNome());
            _ProdutoRepository.save(Produto);
            return new ResponseEntity<Produto>(Produto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Produto> Produto = _ProdutoRepository.findById(id);
        if(Produto.isPresent()){
            _ProdutoRepository.delete(Produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}