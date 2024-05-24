package com.ogu.soonnyang.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "SoonNyang API 명세서",
                description = "SoonNyang 홈페이지 BE API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getMembersAPI() {
        // "/v1/**" 경로에 매칭되는 API를 그룹화하여 문서화
        String[] paths = {"/v1/members/**"};

        return GroupedOpenApi.builder()
                .group("Members")  // 그룹 이름을 설정
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정
                .build();
    }

    @Bean
    public GroupedOpenApi getAuthAPI() {
        // "/v1/**" 경로에 매칭되는 API를 그룹화하여 문서화
        String[] paths = {"/v1/auth/**"};

        return GroupedOpenApi.builder()
                .group("Auth")  // 그룹 이름을 설정
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정
                .build();
    }

    @Bean
    public GroupedOpenApi getCatsAPI() {
        // "/v1/**" 경로에 매칭되는 API를 그룹화하여 문서화
        String[] paths = {"/v1/cats/**"};

        return GroupedOpenApi.builder()
                .group("Cats")  // 그룹 이름을 설정
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정
                .build();
    }

    @Bean
    public GroupedOpenApi getPostAPI() {
        // "/v1/**" 경로에 매칭되는 API를 그룹화하여 문서화
        String[] paths = {"/v1/posts/**"};

        return GroupedOpenApi.builder()
                .group("Posts")  // 그룹 이름을 설정
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정
                .build();
    }

    @Bean
    public GroupedOpenApi getPostImageAPI() {
        // "/v1/**" 경로에 매칭되는 API를 그룹화하여 문서화
        String[] paths = {"/v1/postImages/**"};

        return GroupedOpenApi.builder()
                .group("PostImages")  // 그룹 이름을 설정
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정
                .build();
    }

}