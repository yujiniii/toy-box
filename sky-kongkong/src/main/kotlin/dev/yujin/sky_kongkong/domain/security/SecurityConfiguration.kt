package dev.yujin.sky_kongkong.domain.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfiguration {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain? {
        return httpSecurity
            .authorizeHttpRequests { authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll() // 정적 리소스 허용
                    .requestMatchers("/api/auth/**").permitAll() // api/auth 요청 허용
                    .requestMatchers("/login", "/register").permitAll() // 로그인 및 회원가입 요청 허용
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
            }.csrf { csrf ->
                csrf.disable()
            }.headers { headers ->
                headers.addHeaderWriter(XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
            }.formLogin { formLogin ->
                formLogin
                    .loginPage("/login")
                    .loginProcessingUrl("/api/auth/login")
                    .defaultSuccessUrl("/", true)
            }.logout { logout ->
                logout.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
            }.build()
    }
}