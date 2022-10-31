package com.sinfloo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinfloo.demo.model.Categoria;
import com.sinfloo.demo.repository.ICategoriaRepository;


@Service
@Transactional
public class CategoriaService {
	@Autowired
    private ICategoriaRepository repo;

    public List<Categoria> listAll() {
        return (List<Categoria>) repo.findAll();
    }

    public void save(Categoria product) {
        repo.save(product);
    }

    public Categoria get(long  id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
