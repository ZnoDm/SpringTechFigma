package com.sinfloo.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinfloo.demo.model.Producto;
import com.sinfloo.demo.repository.IProductoRepository;

@Service
@Transactional
public class ProductoService {
    @Autowired
    private IProductoRepository repo;

    public List<Producto> listAll() {
        return repo.findAll();
    }

    public Page<Producto> findPaginated(String search, Pageable pageable) {
        List<Producto> products = repo.searchProduct(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Producto> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Producto> bookPage = new PageImpl<Producto>(list, PageRequest.of(currentPage, pageSize), products.size());

        return bookPage;
    }


    public void save(Producto product) {
        repo.save(product);
    }

    public Producto get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
