package com.sinfloo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.sinfloo.demo.model.Categoria;


@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
