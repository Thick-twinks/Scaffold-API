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
@Table(name = "notifications")
public class Notification {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "entity_data", nullable = false, columnDefinition = "jsonb")
    private String entityData;

    @Column(name = "need_to_show")
    private Boolean needToShow = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "read_at")
    private LocalDateTime readAt;

}
