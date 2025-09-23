package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "materials")
public class Materials {
    @Id
    private UUID id = UUID.randomUUID();

    private Integer quantity;

    private String status;

    @Column(name = "document_link")
    private String documentLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
