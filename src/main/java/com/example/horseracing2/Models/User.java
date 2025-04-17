package com.example.horseracing2.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20)
    private String role;

    @Column(precision = 10, scale = 2)
    private BigDecimal wallet;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
