package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.TaskStatus;
import net.scaffold.io.scaffold.entity.constant.TaskType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    private UUID id = UUID.randomUUID();

    private String title;
    private String description;
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "actual_completion_date")
    private LocalDateTime actualCompletionDate;

    @Column(name = "task_order")
    private Integer order;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType type;

    // References
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private Member creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private Member executor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classifier_id")
    private ViolationClassifier classifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_geopoint_id")
    private Geopoint creatorGeopoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_geopoint_id")
    private Geopoint executorGeopoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confirmation_document_id")
    private Document confirmationDocument;

    @OneToMany(mappedBy = "task")
    private List<WorkChange> workChanges = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
