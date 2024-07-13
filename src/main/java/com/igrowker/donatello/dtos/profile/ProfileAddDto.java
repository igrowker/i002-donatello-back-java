package com.igrowker.donatello.dtos.profile;

import com.igrowker.donatello.auth.entities.CustomUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAddDto {
    CustomUser user;

    @NotNull(message = "Company name cannot be null.")
    @Size(min=2, max=30, message = "Company name must be between 2 and 30 characters")
    String companyName;

    @NotNull(message = "Address cannot be null.")
    @Size(min=2, max=30, message = "Address must be between 2 and 30 characters")
    String address;

    String addressDetails;

    @NotNull(message = "City cannot be null.")
    @Size(min=2, max=30, message = "City must be between 2 and 30 characters")
    String city;

    @NotNull(message = "Postal code cannot be null.")
    @Size(min=2, max=30, message = "Postal code must be between 2 and 30 characters")
    String postalCode;

    @NotNull(message = "Country cannot be null.")
    @Size(min=2, max=30, message = "Country must be between 2 and 30 characters")
    String country;
}
