package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.TaskStatus;
import net.scaffold.io.scaffold.entity.constant.TaskType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private LocalDateTime actualCompletionDate;
    private Integer order;
    @Enumerated(EnumType.STRING)
    private TaskType type;
    private UUID creatorId;
    private UUID executorId;
    private UUID projectId;
    private UUID classifierId;
    private UUID creatorGeopointId;
    private UUID executorGeopointId;
    private UUID confirmationDocumentId;
}

