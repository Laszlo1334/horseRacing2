package com.example.horseracing2.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "races")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Race {
    @Id
    private Integer id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(length = 100)
    private String location;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
