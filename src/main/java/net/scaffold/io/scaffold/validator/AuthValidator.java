package net.scaffold.io.scaffold.validator;

import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.request.RegisterRequestDto;
import net.scaffold.io.scaffold.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class AuthValidator extends AbstractValidator {
    public void prevalidateAuth(LoginRequestDto dto) {
        allNotNull("MISSING_PARAMS",
                dto.email(),
                dto.password()
        );
    }

    public void validateAuth(Member member) {
        notNull(member, "MEMBER_NOT_FOUND");
    }

    public void prevalidateRegister(RegisterRequestDto dto) {
        allNotNull("MISSING_PARAMS",
                dto.email(),
                dto.password(),
                dto.fullName(),
                dto.role()
        );
        //TODO: добавить проверки на регекс
    }

    public void validateRefresh(String id) {
        notNull(id, "INVALID_REFRESH_TOKEN");
    }
}
