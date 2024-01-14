package com.gm2.pdv.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductinfoDTO {
    private long id;

    private String description;

    private int quantity;
}
