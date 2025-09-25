package net.scaffold.io.scaffold.dto.response;

import net.scaffold.io.scaffold.entity.constant.ProjectStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectResponseDto(
        UUID id,
        String name,
        UUID customerId,
        UUID contractorId,
        UUID geopointId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String openingAct,
        ProjectStatus status
) {
}
