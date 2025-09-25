package net.scaffold.io.scaffold.service;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dao.ProjectDao;
import net.scaffold.io.scaffold.entity.Project;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectDao projectDao;

    public List<Project> findAllProjectsByMemberEmail(String email) {
        return projectDao.findAllProjectsByMemberEmail(email);
    }
}