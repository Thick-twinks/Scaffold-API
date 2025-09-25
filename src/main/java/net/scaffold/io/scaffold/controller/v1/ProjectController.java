package net.scaffold.io.scaffold.controller.v1;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dto.request.GetProjectRequestDto;
import net.scaffold.io.scaffold.dto.response.ProjectResponseDto;
import net.scaffold.io.scaffold.processor.ProjectProcessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectProcessor projectProcessor;


    @PostMapping("/get")
    public List<ProjectResponseDto> create(@RequestBody GetProjectRequestDto dto){return projectProcessor.get(dto);}

}
