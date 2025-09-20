package io.comeandcommue.lib.web.loginUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtUtil {

    // HS256 시크릿 (실서비스에선 외부 설정으로 분리 권장)
    private final static Algorithm algorithm = Algorithm.HMAC256(
            "f8I7Tn4Wb8zL9d3Pq6Vr1sFz8Xr2Ek3Gh1m9Yc6Lq3XnPt0a"
    );

    // 검증기는 스레드-세이프하므로 재사용
    private final static JWTVerifier verifier = JWT.require(algorithm).build();

    public static String extractSubject(String token) {
        DecodedJWT decoded = verifier.verify(token); // 서명/만료 검증
        return decoded.getSubject();
    }

    // 필요 시 닉네임 등 다른 클레임 꺼내는 헬퍼도 추가 가능
    public static String extractNickname(String token) {
        DecodedJWT decoded = verifier.verify(token);
        return decoded.getClaim("nickname").asString();
    }
}
