package com.sinfloo.demo.repository;

import com.sinfloo.demo.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RVenta extends JpaRepository<Venta, Integer> {
}
