package org.example.gsabg.security;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import java.sql.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerartoken(String username){

        Date data_atual = new Date(System.currentTimeMillis());

        JwtBuilder buider = Jwts.builder()
                .subject(username)
                .issuedAt(data_atual)
                .expiration(new Date(data_atual.getTime() + (3600000)))
                .signWith(secretKey);
        return buider.compact();
    }

    public String extrairUsername(String token){
        JwtParser parser = Jwts.parser().verifyWith(secretKey).build();

        return parser.parseSignedClaims(token).getPayload().getSubject();
    }


    public boolean verificartoken(String token){
        try{
            JwtParser parser = Jwts.parser().verifyWith(secretKey).build();

            parser.parseSignedClaims(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
