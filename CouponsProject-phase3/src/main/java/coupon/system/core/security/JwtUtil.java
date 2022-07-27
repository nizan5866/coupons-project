package coupon.system.core.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    private final String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    byte[] encodedKey = "super+duper+secret+key+do+you+hear+what+i+tell+you+noob".getBytes(StandardCharsets.UTF_8);
    private final Key decodedKey = new SecretKeySpec(Base64.getDecoder().decode(encodedKey),signatureAlgorithm);

    public String generateToken(ClientDetails clientDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put("clientId",clientDetails.getId());
        claims.put("clientType",clientDetails.getClientType());
        return createToken(claims, clientDetails.getEmail());
    }

    private String createToken(Map<String,Object> claims, String subject){
        Instant now = Instant.now();
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
//				.setExpiration(Date.from(now.plus(10, ChronoUnit.HOURS))).signWith(this.decodedSecretKey)
                .setExpiration(Date.from(now.plus(2, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256,decodedKey)
                .compact();
    }

    private Claims extractAllClaims(String token){
        JwtParser jwtParser = Jwts.parser().setSigningKey(decodedKey);
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    public int extractId(String token){
        return (int) extractAllClaims(token).get("clientId");
    }

    public ClientType extractType(String token){
        return ClientType.valueOf(extractAllClaims(token).get("clientType").toString());
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token){
        try{
            extractAllClaims(token);
            return true;
        }catch (ExpiredJwtException e){
            return false;
        }
    }

    public boolean validateToken(String token, ClientDetails clientDetails){
        final String username = extractUsername(token);
        return (username.equals(clientDetails.getEmail()) && !isTokenExpired(token));
    }


}
