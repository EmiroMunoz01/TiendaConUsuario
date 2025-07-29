package org.example.tienda.dto.producto.administrador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObtenerProductosAdministradorDTO {

        private Integer id;

        private String nombre;

        private String serial;

        private Integer precio;

        private Integer cantidad;

        private Boolean disponibilidad;
}
