package com.igrowker.donatello.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "menu_productos")
public class MenuProduct {

    @EmbeddedId
    private MenuProductPK id;

    @Column(name = "cantidad", nullable = false)
    private Integer amount;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_menu", insertable = false, updatable = false)
    private Menu menu;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Product product;
}
