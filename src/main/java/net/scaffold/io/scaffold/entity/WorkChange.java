package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ViolationKind;
import net.scaffold.io.scaffold.entity.constant.ViolationType;
import net.scaffold.io.scaffold.entity.constant.WorkChangeStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "work_changes")
public class WorkChange {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "new_deadline", nullable = false)
    private LocalDateTime newDeadline;

    @Enumerated(EnumType.STRING)
    private WorkChangeStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
