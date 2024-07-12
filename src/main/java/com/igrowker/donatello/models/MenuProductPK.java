package com.igrowker.donatello.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class MenuProductPK implements Serializable {

    @Column(name = "id_menu")
    private Integer idMenu;

    @Column(name = "id_product")
    private Integer idProduct;
}
