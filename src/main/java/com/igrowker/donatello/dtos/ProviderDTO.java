package com.igrowker.donatello.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ProviderDTO {
    private Long id;
    private String name;
    private String contact;
    private String address;
    private Integer userId;
    private String company;
    private LocalDateTime lastContact;
}
