package net.scaffold.io.scaffold.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scaffold.io.scaffold.dto.request.GetProjectRequestDto;
import net.scaffold.io.scaffold.dto.response.ProjectResponseDto;
import net.scaffold.io.scaffold.mapper.ProjectMapper;
import net.scaffold.io.scaffold.service.MemberService;
import net.scaffold.io.scaffold.service.ProjectService;
import net.scaffold.io.scaffold.validator.AuthValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectProcessor {
    private final MemberService memberService;
    private final AuthValidator authValidator;
    private final ProjectMapper projectMapper;
    private final ProjectService projectService;


    public List<ProjectResponseDto> get(GetProjectRequestDto dto) {
        var member = memberService.findMemberByEmail(dto.email());
        authValidator.validateAuth(member);
        var project = projectService.findAllProjectsByMemberEmail(dto.email());
        return projectMapper.toDtoList(project);
    }
}
