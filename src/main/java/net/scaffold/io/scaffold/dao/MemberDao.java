package net.scaffold.io.scaffold.dao;

import net.scaffold.io.scaffold.entity.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface MemberDao extends JpaRepository<Member, UUID> {

}
