package pt.allanborges.authserviceapi.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.allanborges.authserviceapi.secutiry.dtos.UserDetailsDTO;

import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;


    public String generateToken(final UserDetailsDTO userDetailsDTO) {
        return Jwts.builder()
                .claim("id", userDetailsDTO.getId())
                .claim("name", userDetailsDTO.getName())
                .claim("authorities", userDetailsDTO.getAuthorities())
                .setSubject(userDetailsDTO.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

}