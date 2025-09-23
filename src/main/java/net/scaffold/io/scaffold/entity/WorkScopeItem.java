package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ProjectStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "work_scope_items")
public class WorkScopeItem {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "item_order", nullable = false)
    private Integer order;

    @Column(name = "task_description", nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
