package com.gm2.pdv.pdv.dto.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Campo descrição é obrigatório")
    private String description;

    @NotBlank(message = "Campo preço é obrigatório")
    private BigDecimal price;

    @NotBlank(message = "Campo quantidade é obrigatório")
    private int quantity;
}
