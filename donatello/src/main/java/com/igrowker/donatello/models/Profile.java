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
    private CustomUser user;

    @Column(nullable = false )
    String companyName;
    @Column(nullable = false )
    String address;
    String addressDetails;
    @Column(nullable = false )
    String city;
    @Column(nullable = false )
    String postalCode;
    @Column(nullable = false )
    String country;
}
