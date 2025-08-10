package org.casino.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final org.casino.configs.CustomOAuth2SuccessHandler successHandler;

    // Inject CustomOAuth2SuccessHandler through the constructor
    public SecurityConfig(org.casino.configs.CustomOAuth2SuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключаем CSRF, если у вас API без формы
                .csrf(csrf -> csrf.disable())

                // Все запросы требуют аутентификации
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                // Включаем OAuth2 Login — перенаправление на Keycloak
                .oauth2Login(oauth -> oauth
                        .successHandler(successHandler))

                // (Опционально) если вы хотите ещё и клиентские возможности OAuth2
                .oauth2Client(withDefaults());

        return http.build();
    }
}



