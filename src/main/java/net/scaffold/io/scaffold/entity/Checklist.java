package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ChecklistType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "checklists")
public class Checklist {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Enumerated(EnumType.STRING)
    private ChecklistType type;

    private String document;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
