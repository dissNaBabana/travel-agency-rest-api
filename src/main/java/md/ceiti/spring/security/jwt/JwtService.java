package md.ceiti.spring.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import md.ceiti.spring.entity.dto.jwt.JwtAuthenticatorDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {
    private final Logger LOGGER = LogManager.getLogger(JwtService.class);
    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtAuthenticatorDto generateAuthToken(String email){
        JwtAuthenticatorDto jwtDto = new JwtAuthenticatorDto();
        jwtDto.setToken(generateJwtToken(email));
        jwtDto.setRefreshToken(generateRefreshJwtToken(email));
            return jwtDto;
    }

    public JwtAuthenticatorDto refreshBaseToken(String email, String refreshToken){
        JwtAuthenticatorDto jwtDto = new JwtAuthenticatorDto();
        jwtDto.setToken(generateJwtToken(email));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSingInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        }catch (ExpiredJwtException expEx){
            LOGGER.error("Expired JwtException : "+ expEx);
        }catch (UnsupportedJwtException expEx){
            LOGGER.error("Unsupported JwtException : " + expEx);
        }catch (MalformedJwtException expEx){
            LOGGER.error("Malformed JwtException : " + expEx);
        }catch (SecurityException expEx){
            LOGGER.error("Security JwtException : " + expEx);
        }catch (Exception expEx){
            LOGGER.error("Invalid token : " + expEx);
        }
        return false;
    }

    private String generateJwtToken(String email){
        Date date = Date.from(
                LocalDateTime.now()
                        .plusMinutes(10)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }

    private String generateRefreshJwtToken(String email){
        Date date = Date.from(
                LocalDateTime.now()
                        .plusDays(90)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }

    private SecretKey getSingInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
