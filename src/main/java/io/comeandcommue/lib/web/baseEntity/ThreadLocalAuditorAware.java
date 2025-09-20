package io.comeandcommue.lib.web.baseEntity;

import io.comeandcommue.lib.web.auth.AuthContext;
import io.comeandcommue.lib.web.auth.LoginUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class ThreadLocalAuditorAware implements AuditorAware<String> {

    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        return AuthContext.get().map(LoginUser::id);
    }
}

