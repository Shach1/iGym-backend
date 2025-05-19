package ru.mireadev.igym.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Отключаем CSRF для API
            .authorizeHttpRequests { auth ->
                //auth.anyRequest().permitAll() // Разрешаем все запросы
                auth
                    .requestMatchers("/api/auth/register").permitAll() // Разрешаем регистрацию без аутентификации
                    .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            }
            .httpBasic { } // Используем Basic Auth (можно заменить на JWT)

        return http.build()
    }
}
