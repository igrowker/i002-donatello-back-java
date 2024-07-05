package com.igrowker.donatello.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateDto {
    String name; // en usuario
    // NO SE DEBERIA PODER CAMBIAR ==> email;
    String phone; // en usuario
    String password; // en usuario

    String companyName;
    String address;
    String addressDetails;
    String city;
    String postalCode;
    String country;
}

