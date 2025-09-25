package net.scaffold.io.scaffold.dao;

import net.scaffold.io.scaffold.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectDao extends JpaRepository<Project, UUID> {

    @Query(
            nativeQuery = true,
            value = """
                        SELECT p FROM projects p
                        WHERE p.customer.email = :memberEmail OR p.contractor.email =: memberEmail
                    """
    )
    List<Project> findAllProjectsByMemberEmail(String email);
}
