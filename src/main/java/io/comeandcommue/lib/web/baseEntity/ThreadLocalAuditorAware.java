package io.comeandcommue.lib.web.baseEntity;

import io.comeandcommue.lib.web.authContext.AuthContext;
import io.comeandcommue.lib.web.authContext.LoginUser;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ThreadLocalAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return AuthContext.get().map(LoginUser::id);
    }
}

