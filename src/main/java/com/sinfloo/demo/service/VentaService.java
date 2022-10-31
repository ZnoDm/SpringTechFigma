package com.sinfloo.demo.service;

import com.sinfloo.demo.model.Venta;
import com.sinfloo.demo.repository.RVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VentaService {
    @Autowired
    private RVenta repo;

    public void save(Venta venta){
        repo.save(venta);
    }

    public List<Venta> listar(){
        return repo.findAll();
    }

    public Venta getVenta(int id){
        return repo.findById(id).get();
    }
    
    public void delete(int id) {
        repo.deleteById(id);
    }
}
