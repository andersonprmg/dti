package br.com.dti.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dti.springbootapi.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { }