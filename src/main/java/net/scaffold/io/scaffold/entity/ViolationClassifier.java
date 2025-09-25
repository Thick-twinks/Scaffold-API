package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ViolationKind;
import net.scaffold.io.scaffold.entity.constant.ViolationType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class ViolationClassifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String category;
    @Enumerated(EnumType.STRING)
    private ViolationKind kind;
    @Enumerated(EnumType.STRING)
    private ViolationType type;
    private String name;
    private Integer fixDeadlineDays;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
