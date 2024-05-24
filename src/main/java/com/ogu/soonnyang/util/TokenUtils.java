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

        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey().getBytes())
                .parseClaimsJws(jwtToken).getBody();
    }
}
