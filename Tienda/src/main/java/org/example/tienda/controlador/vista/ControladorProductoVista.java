package org.example.tienda.controlador.vista;


import jakarta.validation.Valid;
import org.example.tienda.dto.producto.administrador.CrearProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.EditarProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.ObtenerProductosAdministradorDTO;
import org.example.tienda.modelo.producto.Producto;
import org.example.tienda.servicio.producto.ServicioProducto;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable int id) {
        ObtenerProductosAdministradorDTO producto = servicioProducto.obtenerProductoPorIdAdministrador(id);

        model.addAttribute("producto", producto);

        return "producto/editar";
    }


    @PostMapping("/editar/id/{id}")
    public String procesarEdicionProducto(@PathVariable Integer id,
                                          @ModelAttribute("producto") @Valid EditarProductoAdministradorDTO dto,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Volver al formulario con errores
            return "producto/editar";
        }

        servicioProducto.editarProductoPorIdAdministrador(dto, id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado correctamente");
        return "redirect:/tienda/producto/listar";  // Rediriges a la lista tras actualizar
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        servicioProducto.eliminarProductoPorIdAdministrador(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado correctamente");
        return "redirect:/tienda/producto/listar"; // redirige a la lista de productos
    }


    @GetMapping("/listar")
    public String listarProductos(Model model) {
        model.addAttribute("productos", servicioProducto.obtenerProductosAdministrador());
        return "producto/lista"; // busca en templates/producto/lista.html
    }

}
