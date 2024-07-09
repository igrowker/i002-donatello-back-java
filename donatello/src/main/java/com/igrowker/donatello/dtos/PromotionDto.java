package com.igrowker.donatello.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionDto {
    private Integer id;

    private String description;

    private LocalDateTime startDate;
    
    private LocalDateTime endDate;

    private Integer userId;
}
