package com.sinfloo.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinfloo.demo.model.DetalleVenta;
import com.sinfloo.demo.repository.RDetalleVenta;

@Service
@Transactional
public class DetalleVentaService {
	@Autowired
	private RDetalleVenta detalleRepo;
	
	public void save(DetalleVenta dv) {
		detalleRepo.save(dv);
	}
}
