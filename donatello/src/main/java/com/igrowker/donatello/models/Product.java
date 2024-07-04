package com.igrowker.donatello.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "nombre", nullable = false , unique = true)
    private String name;

    @Column(name = "descripcion", nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "id_usuario")
    private Integer idUser;

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private CustomUser customUser;
}
