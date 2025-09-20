package io.comeandcommue.lib.web.authContext;

public record
LoginUser(
        String id,
        String nickname,
        String ipAddr,
        DeviceType deviceType,
        String userAgent
) {
    public static LoginUser anonymous(String ip, DeviceType dt, String ua) {
        return new LoginUser(null, null, ip, dt, ua);
    }
}
