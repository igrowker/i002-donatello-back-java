package com.igrowker.donatello.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String name;

    private String phone;

    private String address;

    private Integer userID;
}
