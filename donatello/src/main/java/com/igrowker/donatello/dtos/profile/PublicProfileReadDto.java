package com.igrowker.donatello.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicProfileReadDto {
    String name;
    String mail;
    String phone;
    String companyName;
    String address;
    String addressDetails;
    String city;
    String postalCode;
    String country;
}

