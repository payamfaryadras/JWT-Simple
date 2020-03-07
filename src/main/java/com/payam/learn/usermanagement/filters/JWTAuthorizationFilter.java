package com.payam.learn.usermanagement.filters;

import com.payam.learn.usermanagement.security.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.payam.learn.usermanagement.constans.SecurityConstant.TOKEN_PREFIX;
import static com.payam.learn.usermanagement.constans.SecurityConstant.TOKEN_HEADER;
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader(TOKEN_HEADER);
        if (authorization == null || authorization.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String authorization) {
        String token = authorization.replace(TOKEN_PREFIX, "");
        try {
            String username = JwtUtils.getusernameByToken(token);
            var authorities = ((List<?>) JwtUtils.getTokenBody(token)
                    .get("rol")).stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority))
                    .collect(Collectors.toList());
            if(username !=null && !username.isEmpty()){
                return new UsernamePasswordAuthenticationToken(username,null,authorities);
            }

        } catch (ExpiredJwtException exception) {
            log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
        } catch (SignatureException exception) {
            log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
        }
        catch (RuntimeException exception) {
//                TODO: replace with NotFoundException
            log.warn("User not found");
        }
        return null;
    }

}
