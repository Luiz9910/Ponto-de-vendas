package com.gm2.pdv.pdv.dto.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleDTO {

    @NotBlank(message = "O item da venda obrigatório")
    private long productid;

    @NotBlank(message = "Campo quantidade é obrigatório")
    private int quantity;
}
