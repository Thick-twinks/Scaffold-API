package net.scaffold.io.scaffold.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "geopoints")
public class Geopoint {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String x;

    @Column(nullable = false)
    private String y;

    @CreationTimestamp
    private LocalDateTime createdAt;
}