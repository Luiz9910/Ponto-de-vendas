package com.gm2.pdv.pdv.dto.User;

import com.gm2.pdv.pdv.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @NotBlank(message = "Campo username é obrigatório")
    private String username;

    @NotBlank(message = "Campo password é obrigatório")
    private String password;

    private UserRole role;

    private boolean isEnabled;
}
