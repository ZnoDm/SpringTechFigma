package com.sinfloo.demo.controller;

import com.sinfloo.demo.model.DetalleVenta;
import com.sinfloo.demo.model.Producto;
import com.sinfloo.demo.model.User;
import com.sinfloo.demo.model.Venta;
import com.sinfloo.demo.repository.IUserRepository;
import com.sinfloo.demo.service.DetalleVentaService;
import com.sinfloo.demo.service.ProductoService;
import com.sinfloo.demo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService Ventaservice;

    @Autowired
    private IUserRepository userService;
    
    @Autowired
    private com.sinfloo.demo.service.ProductoService productService;
    @Autowired
    private DetalleVentaService dvService;

    
    private String add_edit_template="/admin/venta/add-edit-venta";
    private String list_template="/admin/venta/list-venta";
    private String list_redirect="redirect:/venta/list";
    
    @GetMapping("/list")
    public String listar(Model model){
        List<Venta>ventas = Ventaservice.listar();
        List<User>users = userService.findAll();
        model.addAttribute("ventas", ventas);
        model.addAttribute("users", users);        
        return list_template;
    }

    @GetMapping("/add")
    public String Agregar(Model model){
        List<Producto> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
    	
    	model.addAttribute("venta", new Venta());
    	model.addAttribute("size", false);
    	
        return add_edit_template;
    }
    
    @PostMapping("/save")
    public String save(@RequestParam(value = "idCantidad[]") List<String> idCantidad, @RequestParam(value = "idPrice[]") List<String> idPrice, @RequestParam(value = "idProducto[]") List<String> idProducto,@RequestParam(value = "fecha") String fecha, RedirectAttributes redirect) throws ParseException {
    	String fechaString = "";
    	fechaString = fecha;
    	Venta venta = new Venta();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        venta.setUsuario(user);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formato.parse(fechaString);
        venta.setFecha(date);
        Ventaservice.save(venta);
        
       for (int i = 0; i < idProducto.size(); i++) {
    	   DetalleVenta dVenta = new DetalleVenta();
    	   dVenta.setCantidad(Integer.parseInt(idCantidad.get(i)));
    	   dVenta.setPrecio(Float.parseFloat(idPrice.get(i)));
    	   Producto pro = productService.get(Long.parseLong(idProducto.get(i)));
    	   pro.restarStock(Integer.parseInt(idCantidad.get(i)));
    	   productService.save(pro);
    	   dVenta.setProducto(pro);
    	   dVenta.setVenta(venta);
    	   
    	   dvService.save(dVenta);
    	   
       }
       redirect.addFlashAttribute("mensaje", "Venta Agregada correctamente")
       			.addFlashAttribute("clase", "success");
       return list_redirect;
    }
    
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
    	List<Producto> productos = productService.listAll();
    	model.addAttribute("productos", productos);
    	
    	Venta venta = Ventaservice.getVenta(id);
    	Boolean size = venta.DetalleSize()>0;
    	model.addAttribute("venta", venta);
    	model.addAttribute("size", size);
    	return add_edit_template;
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model) {
        Ventaservice.delete(id);
        return list_redirect;
    }
}
