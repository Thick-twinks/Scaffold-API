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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID fromMemberId;
    private UUID toMemberId;
    private UUID objectUuid;
    private String content;
    private UUID documentId;
    private Boolean isNotification = false;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
