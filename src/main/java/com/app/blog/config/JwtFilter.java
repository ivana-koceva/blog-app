package com.app.blog.config;

import com.app.blog.controller.impl.UserController;
import com.app.blog.model.dto.UserDetailsDTO;
import com.app.blog.model.exception.AccessDeniedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserController userController;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    public JwtFilter(UserController userController) {
        this.userController = userController;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        if(request.getMethod().equals("GET") && (requestURI.startsWith("/blogs") || requestURI.startsWith("/tags") || requestURI.startsWith("/auth"))){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ") && userController.validate(authorizationHeader).equals("Valid")) {
                        UserDetailsDTO user = userController.userDetails(authorizationHeader);
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), null, List.of(user.getRole())));
                        filterChain.doFilter(request, response);
                        return;
            }
        } catch (Exception ex) {
            exceptionResolver.resolveException(request, response, null, ex);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
