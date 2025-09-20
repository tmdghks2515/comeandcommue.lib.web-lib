package io.comeandcommue.lib.web.loginUser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    final static String AUTH_TOKEN_KEY = "__auth_token_";

    @Override public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ResolvedLoginUser.class)
                && parameter.getParameterType().isAssignableFrom(LoginUser.class);
    }

    @Override public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer container,
            NativeWebRequest request,
            WebDataBinderFactory factory
    ) {
        var req = (HttpServletRequest) request.getNativeRequest();
        String token = extractCookie(req);
        if (token == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return toPrincipal(token, req);
    }

    private static String extractCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        return Arrays.stream(cookies)
                .filter(c -> AUTH_TOKEN_KEY.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    private LoginUser toPrincipal(String authToken, HttpServletRequest req) {
        String ip = RequestClientInfo.resolveClientIp(req);
        String ua = RequestClientInfo.resolveUserAgent(req);
        DeviceType dt = RequestClientInfo.resolveDeviceType(req);
        return new LoginUser(
                JwtUtil.extractSubject(authToken),
                JwtUtil.extractNickname(authToken),
                ip, dt, ua
        );
    }
}
