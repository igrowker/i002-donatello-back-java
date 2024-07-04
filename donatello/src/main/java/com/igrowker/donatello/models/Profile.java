package com.igrowker.donatello.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfiles")
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "id_perfil")
    Integer id;

    @OneToOne
    @JoinColumn(name="usuario", referencedColumnName="id_usuario")
    private CustomUser usuario;

    @Column(nullable = false )
    String nombreEmpresa;
    @Column(nullable = false )
    String direccion;
    String direccionExtra;
    @Column(nullable = false )
    String ciudad;
    @Column(nullable = false )
    String codigoPostal;
    @Column(nullable = false )
    String pais;
}
