package net.scaffold.io.scaffold.service;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dao.MemberDao;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;
}
