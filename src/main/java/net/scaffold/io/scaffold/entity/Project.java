package net.scaffold.io.scaffold.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.entity.constant.ProjectStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Member customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractor_id")
    private Member contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geopoint_id")
    private Geopoint geopoint;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String openingAct;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project")
    private List<WorkScopeItem> workScopeItems = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectSchedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
}
