package net.scaffold.io.scaffold.mapper;

import net.scaffold.io.scaffold.dto.response.ProjectResponseDto;
import net.scaffold.io.scaffold.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class ProjectMapper {

    public abstract ProjectResponseDto toDto(Project project);

    public abstract List<ProjectResponseDto> toDtoList(List<Project> members);

}
