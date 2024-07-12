package com.igrowker.donatello.models;

import com.igrowker.donatello.auth.entities.CustomUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "id_profile")
    Integer id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName="user_id")
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
