package com.payam.learn.usermanagement.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payam.learn.usermanagement.security.JwtUser;
import com.payam.learn.usermanagement.security.JwtUtils;
import com.payam.learn.usermanagement.security.TokenRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.payam.learn.usermanagement.constans.SecurityConstant.AUTH_LOGIN_URL;
import static com.payam.learn.usermanagement.constans.SecurityConstant.TOKEN_HEADER;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(AUTH_LOGIN_URL);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final var tokenRequest = objectMapper.readValue(request.getInputStream(), TokenRequest.class);
            var authenticationToken = new
                    UsernamePasswordAuthenticationToken(tokenRequest.getEmail(), tokenRequest.getPassword());
            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException("Error in reading request body!!!", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        String token = JwtUtils.createToken(jwtUser.getUsername());
        response.setHeader(TOKEN_HEADER, token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage());
    }
}
