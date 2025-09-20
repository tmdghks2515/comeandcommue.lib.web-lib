package io.comeandcommue.lib.web.auth;

import java.util.Optional;

public class AuthContext {

    private static final ThreadLocal<LoginUser> authHolder = new ThreadLocal<>();

    public static void set(LoginUser loginUser) {
        authHolder.set(loginUser);
    }

    public static Optional<LoginUser> get() {
        return Optional.ofNullable(authHolder.get());
    }

    public static void clear() {
        authHolder.remove();
    }
}
