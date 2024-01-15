package com.gm2.pdv.pdv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}
