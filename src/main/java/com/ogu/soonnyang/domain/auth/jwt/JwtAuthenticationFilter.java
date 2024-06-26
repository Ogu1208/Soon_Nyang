package com.ogu.soonnyang.domain.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider jwtTokenProvider;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private String resolveToken(HttpServletRequest request) {
        try {
            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

            LOGGER.info("Header에서 가져온 Authorization token : {}", bearerToken);

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring("Bearer ".length());
                LOGGER.info("Bearer 제거 후 token : {}", token);
                return token;
            }

            LOGGER.warn("Authorization 헤더가 없거나 형식이 올바르지 않습니다.");
            return bearerToken;
        } catch (Exception e) {
            LOGGER.error("resolveToken 메서드에서 예외 발생: ", e);
            e.printStackTrace(); // 스택 트레이스를 콘솔에 출력합니다.
            return null;
        }
    }


    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(servletRequest);
        LOGGER.info("[doFilterInternal] token 값 추출 완료. token : {}", token);

        LOGGER.info("[doFilterInternal] token 값 유효성 체크 시작");
        if (token != null && jwtTokenProvider.validateToken(token)) {
            LOGGER.info("1---------------- 여길 못오는거임?");
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            LOGGER.info("2---------------- 여길 못오는거임?");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.info("3---------------- 여길 못오는거임?");
            LOGGER.info("[doFilterInternal] token 값 유효성 체크 완료");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
