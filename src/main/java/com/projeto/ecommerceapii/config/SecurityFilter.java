package com.projeto.ecommerceapii.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String athorizedHeader = request.getHeader("Authorization");
        System.out.println("qual string vem aqui: " + athorizedHeader);

        if(!Strings.isBlank(athorizedHeader)
                && athorizedHeader.startsWith("Bearer ")){

            String token = athorizedHeader.substring("Bearer ".length());

            Optional<JWTUserData> optUSer = tokenConfig.validateToken(token);



            if( optUSer.isPresent()){
                JWTUserData userData = optUSer.get();

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userData.role());

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userData, null, List.of(authority));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }
        filterChain.doFilter(request,response);


    }
}
