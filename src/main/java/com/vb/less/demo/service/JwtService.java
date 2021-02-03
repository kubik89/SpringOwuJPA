package com.vb.less.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// цей сервіс генерує токени і валідує їх відразу
@Service
public class JwtService {

    //бажано мати нормальний секрет
    private String SECRET_KEY = "rlerjkndfkjsngnkjfdgnkljdfklnmdfgfdlkndfglkmn";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String someVlue) {
        Map<String, Object> claims = new HashMap<>();
        // add some other claims if you wish
        claims.put("key", someVlue);
        return createToken(claims, username);
    }
// створюю токен який буде передаватися,
// .setExpiration -  дата коли він має закінчитись (ніби відраховує) від дати актуальної кількість часу
// .signWith - як зашифрувати.
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
// сюди приходить токен з кожним реквестом -> розшифровується ключ і виконується цепочка методів що вище:
// один метод запускає інший...
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }
}
