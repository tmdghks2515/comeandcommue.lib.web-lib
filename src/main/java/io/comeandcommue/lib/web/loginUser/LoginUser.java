package io.comeandcommue.lib.web.loginUser;

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
