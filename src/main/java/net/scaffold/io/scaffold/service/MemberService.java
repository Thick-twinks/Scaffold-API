package net.scaffold.io.scaffold.service;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dao.MemberDao;
import net.scaffold.io.scaffold.entity.Member;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;

    public Member findMemberByEmail(String email) {
        return memberDao.findMemberByEmail(email);
    }

    public Member createMember(Member member) {
        member.setId(UUID.randomUUID());

        return memberDao.saveMember(member);
    }
}
