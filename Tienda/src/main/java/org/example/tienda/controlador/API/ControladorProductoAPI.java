package org.example.tienda.controlador.API;

import jakarta.validation.Valid;
import org.example.tienda.dto.producto.administrador.CrearProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.EditarProductoAdministradorDTO;
import org.example.tienda.servicio.producto.ServicioProducto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tienda/api/producto")
public class ControladorProductoAPI {


    private final ServicioProducto servicioProducto;

    public ControladorProductoAPI(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok(servicioProducto.obtenerProductosAdministrador());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody @Valid CrearProductoAdministradorDTO crearProductoAdministradorDTO) {
        return ResponseEntity.ok(servicioProducto.crearProductoAdministrador(crearProductoAdministradorDTO));
    }

    @PutMapping("/editar/id/{id}")
    public ResponseEntity<?>editarProductoPorId(@RequestBody @Valid EditarProductoAdministradorDTO editarProductoAdministradorDTO, @PathVariable Integer id) {
        return ResponseEntity.ok(servicioProducto.editarProductoPorIdAdministrador(editarProductoAdministradorDTO, id));
    }

    @PutMapping("/editar/serial/{serial}")
    public ResponseEntity<?>editarProductoPorSerial(@RequestBody @Valid EditarProductoAdministradorDTO editarProductoAdministradorDTO, @PathVariable String serial) {
        return ResponseEntity.ok(servicioProducto.editarProductoPorSerialAdministrador(editarProductoAdministradorDTO, serial));
    }

    @GetMapping("/listar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioProducto.obtenerProductoPorIdAdministrador(id));
    }

    @GetMapping("/listar/serial/{serial}")
    public ResponseEntity<?> buscarPorSerial(@PathVariable String serial) {
        return ResponseEntity.ok(servicioProducto.obtenerProductoPorSerialAdministrador(serial));
    }

    @DeleteMapping("/eliminar/serial/{serial}")
    public ResponseEntity<?> eliminarProductoPorSerial(@PathVariable String serial) {
        servicioProducto.eliminarProductoPorSerialAdministrador(serial);
        return ResponseEntity.noContent().build(); // 204 sin body
    }

    @DeleteMapping("/eliminar/id/{id}")
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Integer id) {
        servicioProducto.eliminarProductoPorIdAdministrador(id);
        return ResponseEntity.noContent().build(); // 204 sin body
    }
}
