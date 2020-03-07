package com.payam.learn.usermanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

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


//    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
//        List<Role> role = (List<Role>) getTokenBody(token)
//                .get(SecurityConstant.ROLE_CLIMS);
//        List<SimpleGrantedAuthority> authorities =
//                role.stream().map(rol -> new SimpleGrantedAuthority(rol.getRoleName())).collect(Collectors.toList());
//        return authorities;
//    }


}
