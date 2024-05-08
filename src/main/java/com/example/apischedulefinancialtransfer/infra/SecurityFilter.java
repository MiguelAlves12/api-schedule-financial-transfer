package com.example.apischedulefinancialtransfer.infra;

import com.example.apischedulefinancialtransfer.repositories.UserRepository;
import com.example.apischedulefinancialtransfer.services.TokenService;
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
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String token = this.recoverToken(request);
       if(token != null){
           String email = tokenService.validateToken(token);
           UserDetails user = userRepository.findByEmail(email);

           UsernamePasswordAuthenticationToken authetication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(authetication);
       }
       filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
