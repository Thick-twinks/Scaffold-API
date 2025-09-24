package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.WorkChangeStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class WorkChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID taskId;
    private LocalDateTime newDeadline;
    @Enumerated(EnumType.STRING)
    private WorkChangeStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
