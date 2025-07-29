package org.example.tienda.servicio.producto;

import org.example.tienda.dto.producto.administrador.CrearProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.EditarProductoAdministradorDTO;
import org.example.tienda.dto.producto.administrador.ObtenerProductosAdministradorDTO;

import java.util.List;

public interface ImplementacionProducto {


    public List<ObtenerProductosAdministradorDTO> obtenerProductosAdministrador();

    public ObtenerProductosAdministradorDTO obtenerProductoPorIdAdministrador(Integer id);

    public ObtenerProductosAdministradorDTO obtenerProductoPorSerialAdministrador(String serial);


    public CrearProductoAdministradorDTO crearProductoAdministrador(CrearProductoAdministradorDTO producto);

    public void eliminarProductoPorIdAdministrador(Integer id);

    public void eliminarProductoPorSerialAdministrador(String serial);

    public EditarProductoAdministradorDTO editarProductoPorIdAdministrador(EditarProductoAdministradorDTO producto, Integer id);

    public EditarProductoAdministradorDTO editarProductoPorSerialAdministrador(EditarProductoAdministradorDTO producto, String serial);


}
