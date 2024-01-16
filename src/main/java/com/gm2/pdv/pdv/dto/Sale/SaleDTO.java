package com.gm2.pdv.pdv.dto.Sale;

import com.gm2.pdv.pdv.dto.Product.ProductSaleDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    @NotNull
    private long userid;
    private List<ProductSaleDTO> items;
}
