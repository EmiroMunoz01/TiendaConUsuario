package org.example.tienda.repositorio;

import org.example.tienda.modelo.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Integer> {

    Optional<Producto> findProductoBySerial(String serial);


    void deleteProductoBySerial(String serial);

}
