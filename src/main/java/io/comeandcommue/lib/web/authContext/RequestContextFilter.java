package io.comeandcommue.lib.web.authContext;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;

public class RequestContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authToken = extractCookie(httpRequest);
            if (authToken != null) {
                AuthContext.set(toLoginUser(authToken, httpRequest));
            }

            chain.doFilter(request, response);
        } finally {
            AuthContext.clear(); // 반드시 clear 해줘야 함! (메모리 누수 방지)
        }
    }

    private static String extractCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        return Arrays.stream(cookies)
                .filter(c -> "__auth_token_".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    private LoginUser toLoginUser(String authToken, HttpServletRequest req) {
        String ip = RequestClientInfo.resolveClientIp(req);
        String ua = RequestClientInfo.resolveUserAgent(req);
        DeviceType dt = RequestClientInfo.resolveDeviceType(req);
        return new LoginUser(JwtUtil.extractSubject(authToken), JwtUtil.extractNickname(authToken), ip, dt, ua);
    }
}
