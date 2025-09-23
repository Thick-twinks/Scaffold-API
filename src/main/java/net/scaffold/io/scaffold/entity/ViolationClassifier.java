package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ChecklistType;
import net.scaffold.io.scaffold.entity.constant.ViolationKind;
import net.scaffold.io.scaffold.entity.constant.ViolationType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "violation_classifiers")
public class ViolationClassifier {
    @Id
    private UUID id = UUID.randomUUID();

    private String category;

    @Enumerated(EnumType.STRING)
    private ViolationKind kind;

    @Enumerated(EnumType.STRING)
    private ViolationType type;

    private String name;

    @Column(name = "fix_deadline_days")
    private Integer fixDeadlineDays;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
