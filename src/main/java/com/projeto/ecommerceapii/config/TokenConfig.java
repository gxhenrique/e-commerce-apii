package com.projeto.ecommerceapii.config;


import com.projeto.ecommerceapii.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenConfig {


    private final SecretKey key;

    public TokenConfig() {
        this.key = Jwts.SIG.HS256.key().build();
    }

    public String generateToken(Usuario usuario) {

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("userId", usuario.getId())
                .claim("role", usuario.getRole().name())
                .claim("clienteId", usuario.getCliente().getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 84000000))
                .signWith(key)
                .compact();
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {

            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return Optional.of(
                    new JWTUserData(
                            claims.get("userId", Long.class),
                            claims.get("clienteId", Long.class),
                            claims.getSubject(),
                            claims.get("role", String.class)
                    )
            );

        } catch (JwtException | IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
