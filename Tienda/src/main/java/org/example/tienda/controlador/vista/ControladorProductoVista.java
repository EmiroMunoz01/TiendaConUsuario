package org.example.tienda.controlador.vista;


import jakarta.validation.Valid;
import org.example.tienda.dto.producto.administrador.CrearProductoAdministradorDTO;
import org.example.tienda.servicio.producto.ServicioProducto;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tienda/producto")
public class ControladorProductoVista {

    private final ServicioProducto servicioProducto;

    public ControladorProductoVista(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new CrearProductoAdministradorDTO());
        return "producto/crear"; // busca en templates/producto/crear.html
    }

    @PostMapping("/crear")
    public String guardarProducto(@ModelAttribute("producto") @Valid CrearProductoAdministradorDTO producto,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "producto/crear";
        }

        servicioProducto.crearProductoAdministrador(producto);
        return "redirect:/tienda/producto/listar";

    }


    @GetMapping("/listar")
    public String listarProductos(Model model) {
        model.addAttribute("productos", servicioProducto.obtenerProductosAdministrador());
        return "producto/lista"; // busca en templates/producto/lista.html
    }

}
