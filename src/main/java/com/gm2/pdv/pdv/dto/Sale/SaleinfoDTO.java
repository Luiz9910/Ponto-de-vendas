package com.gm2.pdv.pdv.dto.Sale;

import com.gm2.pdv.pdv.dto.Product.ProductinfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleinfoDTO {
    private String user;
    private String date;
    private BigDecimal total;
    private List<ProductinfoDTO> products;
}
