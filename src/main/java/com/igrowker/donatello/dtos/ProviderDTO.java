package com.igrowker.donatello.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ProviderDTO {
    private Long id;
    private String name;
    private String contact;
    private String address;
    private Integer userId;
}
