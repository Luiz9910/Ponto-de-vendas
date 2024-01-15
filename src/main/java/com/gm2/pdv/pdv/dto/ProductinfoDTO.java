package com.gm2.pdv.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductinfoDTO {
    private long id;
    private String description;
    private BigDecimal price;
    private int quantity;
}
