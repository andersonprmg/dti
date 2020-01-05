package br.com.dti.springbootapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.dti.springbootapi.entity.Produto;
import br.com.dti.springbootapi.model.ResponseModel;
import br.com.dti.springbootapi.repository.ProdutoRepository;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ProdutoController {
    @Autowired
    private ProdutoRepository _ProdutoRepository;

    @RequestMapping(value = "/")
	public String index() {
		return "index";
    }
    
    
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
    public ResponseModel Post(@Valid @RequestBody Produto Produto)
    {
        try {
            _ProdutoRepository.save(Produto);
            return new ResponseModel(1,"Registro salvo com sucesso!");
        } catch (Exception e) {
            return new ResponseModel(0,e.getMessage());
        }
        
    }

    @RequestMapping(value = "/produto/{id}", method =  RequestMethod.PUT)
    public ResponseModel Put(@PathVariable(value = "id") long id, @Valid @RequestBody Produto newProduto)
    {
        try {
            Optional<Produto> oldProduto = _ProdutoRepository.findById(id);
            if(oldProduto.isPresent()){
                Produto Produto = oldProduto.get();
                Produto.setNome(newProduto.getNome());
                Produto.setQuantidade(newProduto.getQuantidade());
                Produto.setValor(newProduto.getValor());
                _ProdutoRepository.save(Produto);
                return new ResponseModel(1,"Registro alterado com sucesso!");
            }
            else
                return new ResponseModel(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name());
        }catch(Exception e) {

            return new ResponseModel(0,e.getMessage());
        }        
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
    public ResponseModel Delete(@PathVariable(value = "id") long id)
    {
        Optional<Produto> Produto = _ProdutoRepository.findById(id);
        if(Produto.isPresent()){
            _ProdutoRepository.delete(Produto.get());
            //return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseModel(1,"Registro excluido com sucesso!");
        }
        else
            return new ResponseModel(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()); 
    }

}