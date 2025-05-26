package com.example.egy_tour.security;

import com.example.egy_tour.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("2f330f0c162df2fd6f6a4a04cd5c1c82d1a6b9c7a6d55874ac716fa9d7268ab1b360e602472eba3ad8cbea2bf4fbe345ff1afe87e81be70e42cf6055c6f7721ef7e7e0f07f46ba407181669e8027396221cacfe7a83dd609e21667a272be46ea4aedd169754d8b16a3b7c782600bf79f834d5fa1cc3bc4231ec3a9ad4c0be5ce7ea03617a5094e5a8292069f8b9a7742fa2a3c73e7684c6a784bb2558e676b9b08ab4c517ed88449cc1da19f61fbb27bdbe7fdf9545a8af2ed3a97e9a12a4612c05181555551bbd2b061e753d48530c2fcabb9d3ce75aeaf411a91fd588abfcf9a51625066af891b0cf40d0adc827d02273c169555b9ce2f93a481fe9a91dace")
    private String jwtSecret;

    @Value("86400000") // 1 day in milliseconds
    private int jwtExpirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), Jwts.SIG.HS512) // Use Jwts.SIG instead of deprecated SignatureAlgorithm
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }
}