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
@Table(name = "documents")
public class Document {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "c3_link", nullable = false)
    private String c3Link;

    @Column(name = "additional_info")
    private String additionalInfo;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
