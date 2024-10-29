package com.zeze.board_back.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zeze.board_back.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtProvider JwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
         
            String token = parseBearerToken(request);

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String email = JwtProvider.validate(token);
            if (email == null) {
                filterChain.doFilter(request, response);
                return;
            }

            AbstractAuthenticationToken authenticaitonToken =
                new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
            authenticaitonToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticaitonToken);

            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
        
    }

    private String parseBearerToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) return null;

        String token = authorization.substring(7);
        return token;
    }
}
