package com.ogu.soonnyang.util;

import com.ogu.soonnyang.domain.auth.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUtils {

    private final JwtProperties jwtProperties;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public Claims getClaimsFromRequest(HttpServletRequest request){
        String jwtToken = request.getHeader(AUTHORIZATION_HEADER);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring("Bearer ".length());
        }

        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey().getBytes())
                .parseClaimsJws(jwtToken).getBody();
    }

    private String resolveToken(HttpServletRequest request) {
        try {
            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

            log.info("Header에서 가져온 Authorization token : {}", bearerToken);

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring("Bearer ".length());
                log.info("Bearer 제거 후 token : {}", token);
                return token;
            }

            log.warn("Authorization 헤더가 없거나 형식이 올바르지 않습니다.");
            return bearerToken;
        } catch (Exception e) {
            log.error("resolveToken 메서드에서 예외 발생: ", e);
            e.printStackTrace(); // 스택 트레이스를 콘솔에 출력합니다.
            return null;
        }
    }
}
