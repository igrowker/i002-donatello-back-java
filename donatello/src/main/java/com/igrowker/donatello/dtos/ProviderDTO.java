package com.igrowker.donatello.dtos;

import com.igrowker.donatello.auth.entities.CustomUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderDTO {
    private Long id;
    private String name;
    private String contact;
    private String address;
    private CustomUser user;
}
