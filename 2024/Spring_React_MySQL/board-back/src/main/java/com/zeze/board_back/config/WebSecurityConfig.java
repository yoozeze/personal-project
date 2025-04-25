// package com.zeze.board_back.config;

// import java.io.IOException;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
// import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.AuthenticationEntryPoint;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import com.zeze.board_back.filter.JwtAuthenticationFilter;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor

// public class WebSecurityConfig {
    
//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Bean
//     protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity

//             .cors(cors -> cors
//                 .configurationSource(corsConfigurationSource())
//             )
//             .csrf(CsrfConfigurer::disable)
//             .httpBasic(HttpBasicConfigurer::disable)
//             .sessionManagement(sessionManagement -> sessionManagement
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )
//             .authorizeHttpRequests(request -> request
//                 .requestMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()
//                 .requestMatchers(HttpMethod.GET, "/api/v1/board/**", "/api/v1/user/*").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .exceptionHandling(exceptionHandling -> exceptionHandling
//                 .authenticationEntryPoint(new FailedAuthenticationEntryPoint())
//             )
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
//         return httpSecurity.build();
//     }

//     // @Bean
//     // protected CorsConfigurationSource corsConfigurationSource() {

//     //     CorsConfiguration configuration = new CorsConfiguration();
//     //     configuration.addAllowedOrigin("*");
//     //     configuration.addAllowedMethod("*");
//     //     configuration.addExposedHeader("*");

//     //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     //     source.registerCorsConfiguration("/**", configuration);

//     //     return source;

//     // }
//     @Bean
//     protected CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.addAllowedOrigin("http://localhost:3000");  // React 앱 주소를 명시적으로 설정
//         configuration.addAllowedMethod("*");  // 모든 HTTP 메서드 허용
//         configuration.addAllowedHeader("*");  // 모든 헤더 허용
//         configuration.addExposedHeader("Authorization");  // Authorization 헤더를 응답으로 노출

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);

//         return source;
//     }
// }

// class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

//     @Override
//     public void commence(HttpServletRequest request, HttpServletResponse response,
//             AuthenticationException authException) throws IOException, ServletException {
        
//         response.setContentType("application/json");
//         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//         response.getWriter().write("{ \"code\": \"AF\", \"message\": \" Authorization Failed.\" }");
//     }
    
// }
package com.zeze.board_back.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.zeze.board_back.filter.JwtAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(cors -> cors
                .configurationSource(corsConfigurationSource())  // CORS 설정 적용
            )
            .csrf(CsrfConfigurer::disable)
            .httpBasic(HttpBasicConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션을 사용하지 않도록 설정
            )
            .authorizeHttpRequests(request -> request
                .requestMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()  // 자유롭게 접근할 수 있는 URL 설정
                .requestMatchers(HttpMethod.GET, "/api/v1/board/**", "/api/v1/user/*").permitAll()  // GET 요청에 대해서는 자유롭게 접근
                .anyRequest().authenticated()  // 나머지 요청은 인증을 요구
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new FailedAuthenticationEntryPoint())  // 인증 실패 처리
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가
        
        return httpSecurity.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // React 앱 URL을 Netlify의 도메인으로 설정
        configuration.addAllowedOrigin("https://mococoboard.netlify.app/");  // Netlify에서 배포된 React 앱 주소를 추가
        
        configuration.addAllowedMethod("*");  // 모든 HTTP 메서드 허용 (GET, POST, PUT, DELETE 등)
        configuration.addAllowedHeader("*");  // 모든 헤더 허용
        configuration.addExposedHeader("Authorization");  // 응답에서 Authorization 헤더를 노출
        
        // CORS 매핑을 "/**"로 등록하여 모든 요청에 대해 CORS를 처리
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

// 인증 실패 처리 클래스
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 상태 코드 설정
        response.getWriter().write("{ \"code\": \"AF\", \"message\": \" Authorization Failed.\" }");  // JSON 응답
    }
    
}
