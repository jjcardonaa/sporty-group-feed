package com.sportygroup.feed.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.sportygroup.feed.constants.ControllerConstants.API_INTERNAL_VERSION;

/**
 * Open api configurations
 */
@Configuration
public class OpenApiConfiguration {

    public static final String EXERCISE_LICENSE = "Exercise License";
    private static final String API_TITLE = "Sporty Group Service";
    private static final String VERSION = "1.0";
    private static final String GROUP_NAME_PUBLIC_V1 = String.format("%s v%s", API_TITLE + " public", VERSION);
    private static final String API_DESCRIPTION = "Sporty Group Service API";
    private static final String TEST_URL = "http://www.test.com";

    @Bean
    public OpenAPI argOpenApi() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(VERSION)
                .termsOfService(TEST_URL)
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name(EXERCISE_LICENSE)
                .url(TEST_URL);
    }

    /**
     * Configuration for public API v1.
     * This configuration groups the public API endpoints under a specific version.
     */
    @Component
    public static class PublicOpenApiConfiguration {
        @Bean
        public GroupedOpenApi publicApiV1() {
            return GroupedOpenApi.builder()
                    .group(GROUP_NAME_PUBLIC_V1)
                    .pathsToMatch(API_INTERNAL_VERSION + "/**", "v1" + "/**")
                    .build();
        }
    }
}