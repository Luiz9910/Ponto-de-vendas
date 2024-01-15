package com.gm2.pdv.pdv.dto;

import com.gm2.pdv.pdv.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;

    private String name;

    private boolean isEnabled;
}
