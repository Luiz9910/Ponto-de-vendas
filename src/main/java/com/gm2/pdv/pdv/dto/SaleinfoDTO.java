package com.gm2.pdv.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleinfoDTO {
    private String user;

    private String date;

    private List<ProductinfoDTO> products;
}
