package net.scaffold.io.scaffold.dao;

import net.scaffold.io.scaffold.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberDao extends JpaRepository<Member, UUID> {

    @Query(
            nativeQuery = true,
            value = """
                        SELECT * FROM member m
                        WHERE m.email = :email
                    """
    )
    Member findMemberByEmail(String email);
}
