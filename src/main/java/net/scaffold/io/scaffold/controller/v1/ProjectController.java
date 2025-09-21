package net.scaffold.io.scaffold.controller.v1;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.processor.ProjectProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectProcessor projectProcessor;
}
