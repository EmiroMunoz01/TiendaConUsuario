package org.example.tienda.servicio.producto;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.tienda.dto.producto.administrador.CrearProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.EditarProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.ObtenerProductosAdministradorDTO;
import org.example.tienda.modelo.producto.Producto;
import org.example.tienda.repositorio.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto implements ImplementacionProducto {

    //inyeccion de la dependencia mediante el constructor.
    private final RepositorioProducto repositorioProducto;

    public ServicioProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public List<ObtenerProductosAdministradorDTO> obtenerProductosAdministrador() {

        List<Producto> productos = repositorioProducto.findAll();
        List<ObtenerProductosAdministradorDTO> productosAdministrador = new ArrayList<>();

        for (Producto producto : productos) {
            ObtenerProductosAdministradorDTO dto = new ObtenerProductosAdministradorDTO();
            dto.setId(producto.getId());
            dto.setNombre(producto.getNombre());
            dto.setSerial(producto.getSerial());
            dto.setPrecio(producto.getPrecio());
            dto.setCantidad(producto.getCantidad());
            dto.setDisponibilidad(producto.getDisponibilidad());
            productosAdministrador.add(dto);
        }
        return productosAdministrador;
    }

    @Override
    public ObtenerProductosAdministradorDTO obtenerProductoPorIdAdministrador(Integer id) {

        Producto producto = repositorioProducto.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el producto con id: " + id));

        ObtenerProductosAdministradorDTO dto = new ObtenerProductosAdministradorDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setSerial(producto.getSerial());
        dto.setPrecio(producto.getPrecio());
        dto.setCantidad(producto.getCantidad());
        dto.setDisponibilidad(producto.getDisponibilidad());

        return dto;
    }

    @Override
    public ObtenerProductosAdministradorDTO obtenerProductoPorSerialAdministrador(String serial) {
        Producto producto = repositorioProducto.findProductoBySerial(serial).orElseThrow(() -> new EntityNotFoundException("No se encontro el producto con id: " + serial));

        ObtenerProductosAdministradorDTO dto = new ObtenerProductosAdministradorDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setSerial(producto.getSerial());
        dto.setPrecio(producto.getPrecio());
        dto.setCantidad(producto.getCantidad());
        dto.setDisponibilidad(producto.getDisponibilidad());

        return dto;
    }

    @Override
    public CrearProductoAdministradorDTO crearProductoAdministrador(CrearProductoAdministradorDTO producto) {

        //buscaremos el producto si existe
        Optional<Producto> productoExistente = repositorioProducto.findProductoBySerial(producto.getSerial());

        //si el producto existe, se generara el error por duplicidad
        if (productoExistente.isPresent()) {
            throw new IllegalArgumentException("El producto con el serial: ---" + producto.getSerial() + "--- ya existe en la base de datos");
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(producto.getNombre());
        nuevoProducto.setSerial(producto.getSerial());
        nuevoProducto.setCantidad(producto.getCantidad());
        nuevoProducto.setPrecio(producto.getPrecio());
        nuevoProducto.setDisponibilidad(producto.getCantidad() != null && producto.getCantidad() > 0);

        repositorioProducto.save(nuevoProducto);

        return producto;
    }

    @Override
    public void eliminarProductoPorIdAdministrador(Integer id) {

        Producto buscarProducto = repositorioProducto.findById(id).orElseThrow(() -> new EntityNotFoundException("El producto con el id " + id + " no existe."));

        repositorioProducto.delete(buscarProducto);

    }

    @Transactional
    @Override
    public void eliminarProductoPorSerialAdministrador(String serial) {

        Producto buscarProducto = repositorioProducto.findProductoBySerial(serial).orElseThrow(() -> new EntityNotFoundException("El producto con el serial " + serial + " no existe."));

        repositorioProducto.deleteProductoBySerial(serial);
    }

    @Override
    public EditarProductoAdministradorDTO editarProductoPorIdAdministrador(EditarProductoAdministradorDTO producto, Integer id) {

        Producto buscarProducto = repositorioProducto.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto con el id " + id + " no existe."));


        buscarProducto.setNombre(producto.getNombre());
        buscarProducto.setSerial(producto.getSerial());
        buscarProducto.setPrecio(producto.getPrecio());
        buscarProducto.setCantidad(producto.getCantidad());
        buscarProducto.setDisponibilidad(producto.getCantidad() != null && producto.getCantidad() > 0);

        repositorioProducto.save(buscarProducto);

        return producto;
    }

    @Override
    public EditarProductoAdministradorDTO editarProductoPorSerialAdministrador(EditarProductoAdministradorDTO producto, String serial) {

        Producto buscarProducto = repositorioProducto.findProductoBySerial(serial).orElseThrow(() -> new EntityNotFoundException("Producto con el id " + serial + " no existe."));

        buscarProducto.setNombre(producto.getNombre());
        buscarProducto.setSerial(producto.getSerial());
        buscarProducto.setPrecio(producto.getPrecio());
        buscarProducto.setCantidad(producto.getCantidad());
        buscarProducto.setDisponibilidad(producto.getCantidad() != null && producto.getCantidad() > 0);

        repositorioProducto.save(buscarProducto);

        return producto;
    }

}