package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ProjectStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private UUID customerId;
    private UUID contractorId;
    private UUID geopointId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String openingAct;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
}
