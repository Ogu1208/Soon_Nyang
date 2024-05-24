package com.ogu.soonnyang.config.security;

import com.ogu.soonnyang.domain.auth.jwt.JwtAccessDeniedHandler;
import com.ogu.soonnyang.domain.auth.jwt.JwtAuthenticationEntryPoint;
import com.ogu.soonnyang.domain.auth.jwt.JwtAuthenticationFilter;
import com.ogu.soonnyang.domain.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) // REST API는 UI를 사용하지 않으므로 기본 설정 비활성화
                .csrf(AbstractHttpConfigurer::disable) // REST API는 CSRF 보호가 필요 없으므로 비활성화
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성화

                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/v1/auth/**")).permitAll() // 가입 및 로그인 주소는 허용
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll() // product로 시작하는 GET 요청은 허용
                        .requestMatchers(new AntPathRequestMatcher("**exception**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/healthCheck")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/favicon.ico")).permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**", "/swagger/**").permitAll()

                        // enable h2-console
                        .requestMatchers(PathRequest.toH2Console()).permitAll() // H2 콘솔 접근 허용
                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 접근 허용

                        .anyRequest().authenticated()) // 그 외 모든 요청은 인증 필요


                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // H2 콘솔 사용을 위한 설정

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/v3/api-docs", "/swagger-resources/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger/**", "/sign-api/exception", "/swagger-ui/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
