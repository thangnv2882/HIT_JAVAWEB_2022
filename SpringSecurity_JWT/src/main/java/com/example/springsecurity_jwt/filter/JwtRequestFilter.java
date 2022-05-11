package com.example.springsecurity_jwt.filter;

import com.example.springsecurity_jwt.services.impl.MyUserDetailsService;
import com.example.springsecurity_jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");


            String username = null;
            String jwt = null;

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                username = jwtTokenUtil.extractUsername(jwt);
            }

            if(username != null) {
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                if(jwtTokenUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, null
                            );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
        }

        filterChain.doFilter(request, response);

    }
}
