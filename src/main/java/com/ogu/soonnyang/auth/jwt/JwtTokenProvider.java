package com.ogu.soonnyang.auth.jwt;

import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.member.exception.MemberNotFoundException;
import com.ogu.soonnyang.domain.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService; // Spring Security 에서 제공하는 서비스 레이어
    private final MemberRepository memberRepository;
    private final JwtProperties jwtProperties;
    private final long tokenValidMillisecond = 1000L * 60 * 60 * 24 * 7; // 7일 토큰 유효
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 예제 13.12
    // JWT 토큰 생성
    public String createToken(String userUid, List<String> roles) {
        LOGGER.info("[createToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userUid); //sub가 필요없긴 하지만 쓰는 메소드가 있어서 일단 쓰기로
        Member member = memberRepository.findById(Long.valueOf(userUid))
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + userUid));
        //여기서 token에 필요한 정보들을 담을 수 있음
        claims.put("email", userUid);
        claims.put("member_id", member.getMemberId());
        claims.put("nickname", member.getNickname());

        Date now = new Date();
        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .setSubject(member.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 암호화 알고리즘, secret 값 세팅
                .compact();

        LOGGER.info("[createToken] 토큰 생성 완료");
        return token;
    }

    // 예제 13.13
    // JWT 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}",
                userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }


    // 예제 13.14
    // JWT 토큰에서 회원 구별 정보 추출
    public String getUsername(String token) {
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        String info = Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token).getBody()
                .getSubject();
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        return info;
    }

    // JWT 토큰의 유효성 + 만료일 체크
    public boolean validateToken(String token) {
        LOGGER.info("[validateToken] 토큰 유효 체크 시작");
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {

            LOGGER.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {

            LOGGER.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {

            LOGGER.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {

            LOGGER.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}