package com.example.horseracing2.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "horses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Horse {
    @Id
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    private Integer age;

    @Column(length = 50)
    private String breed;

    @Column(length = 50)
    private String rider;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
