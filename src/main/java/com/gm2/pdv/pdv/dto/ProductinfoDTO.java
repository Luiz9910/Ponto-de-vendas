package com.gm2.pdv.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductinfoDTO {
    private long id;

    private String description;

    private int quantity;
}
