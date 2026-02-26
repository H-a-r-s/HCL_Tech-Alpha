package com.harsh.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Configuration
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        Converter<org.springframework.security.oauth2.jwt.Jwt, Mono<AbstractAuthenticationToken>> authConverter =
                jwt -> {
                    String role = jwt.getClaimAsString("role"); // USER / ADMIN
                    var authorities = (role == null || role.isBlank())
                            ? List.<SimpleGrantedAuthority>of()
                            : List.of(new SimpleGrantedAuthority("ROLE_" + role));
                    return Mono.just(new JwtAuthenticationToken(jwt, authorities, jwt.getSubject()));
                };

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> {})
                .authorizeExchange(ex -> ex
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .pathMatchers("/api/auth/**").permitAll()
                        .pathMatchers("/actuator/**").permitAll()


                        .pathMatchers(HttpMethod.GET, "/api/products/**", "/api/brands/**", "/api/categories/**").permitAll()


                        .pathMatchers("/api/admin/**").hasRole("ADMIN")
                        .pathMatchers("/api/admin/inventory/**").hasRole("ADMIN")

                        .pathMatchers("/cart/**").authenticated()
                        .pathMatchers("/api/orders/**").authenticated()
                        .pathMatchers("/api/v1/notifications/**").authenticated()
                        .pathMatchers("/api/inventory/**").authenticated()


                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtDecoder(jwtDecoder())
                                .jwtAuthenticationConverter(authConverter)
                        )
                )
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKey key = new SecretKeySpec(
                jwtSecret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );
        return NimbusReactiveJwtDecoder.withSecretKey(key).build();
    }
}