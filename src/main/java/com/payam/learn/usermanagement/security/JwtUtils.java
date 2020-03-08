package com.payam.learn.usermanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.payam.learn.usermanagement.constans.SecurityConstant.*;

public class JwtUtils {




    public static String createToken(String email) {
        var signingKey = SECRET_KEY.getBytes();
        var token = Jwts.builder()
                .setHeaderParam("type", TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("rol", "roles")
                .compact();
        return TOKEN_PREFIX.concat(token);

    }


    public static Claims getTokenBody(String token){
        var signingKey = SECRET_KEY.getBytes();
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(signingKey))
                .parseClaimsJws(token)
                .getBody();
    }


    public static String getusernameByToken(String token){
        return getTokenBody(token).getSubject();
    }

//TODO
    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return authorities;
    }


}
