package net.scaffold.io.scaffold.validator;

import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.entity.Member;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthValidator extends AbstractValidator {
    public void prevalidateLogin(LoginRequestDto dto) {
        allNotNull("MISSING_PARAMS",
                dto.email(),
                dto.password()
        );
    }

    public void validateLogin(Member member) {
        notNull(member, "MEMBER_NOT_FOUND");
    }

    public void validateId(UUID id) {notNull(id, "ID_NOT_FOUND");}
}
