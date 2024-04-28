package com.ogu.soonnyang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JPAConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("순냥이 집사 호소인"); // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때 수정
    }
}
