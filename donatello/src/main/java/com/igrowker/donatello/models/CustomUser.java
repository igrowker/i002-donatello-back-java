package com.igrowker.donatello.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
public class CustomUser implements UserDetails  {
    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    Integer id;

    @Column(nullable = false )
    String name;

    @Column(nullable = false , unique = true)
    String mail;

    @Column(nullable = false )
    String password;

    @Column(nullable = false )
    String phone;

    /*
    @OneToMany(fetch = FetchType.LAZY)
    Usuarios - Proveedores: (1:N)
    Usuarios - Clientes: (1:N)
    todo verificar si esta bien que la relacion este en productos, deberia ser OneToMany (1 usuario a muchos productos)? -> Usuarios - Productos: (1:N)
    Usuarios - Menús: (1:N)
    Usuarios - Finanzas: (1:N)
    Usuarios - Promociones: (1:N)
    Usuarios - Notificaciones: (1:N)

    MANY TO MANY
    Menús - Productos (Menú_Productos): Relación de muchos a muchos (N:N) a través de la entidad asociativa Menú_Productos. Un menú puede contener múltiples productos, y un producto puede estar en múltiples menús.
     */


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

}
