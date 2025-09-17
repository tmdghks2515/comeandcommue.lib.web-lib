package io.comeandcommue.web.loginUser;

public record UserPrincipal(
        String id,
        String nickname,
        String ipAddr,
        DeviceType deviceType,
        String userAgent
) { }
