package io.comeandcommue.web.lib.loginUser;

public record UserPrincipal(
        String id,
        String nickname,
        String ipAddr,
        DeviceType deviceType,
        String userAgent
) { }
