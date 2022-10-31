package com.sinfloo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinfloo.demo.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
	  @Query("select p from Producto p " +
	            //"join p.productType pt " +
	            "where 1=1" +
	            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
	            "       or upper(p.brand) like concat('%', upper(?1), '%') " +
	            "       or upper(p.madein) like concat('%', upper(?1), '%')" +
	            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
	            ")")
	    List<Producto> searchProduct(String criteria);
}
