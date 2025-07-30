package net.javaguides.ems.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import net.javaguides.ems.entity.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtil{
    private static final String SECRET_KEY_STRING="cAR1EBLvGB7FHK4UlfluEDdZr0IPe8yO";
    private final SecretKey SECRETKEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    public String generateToken(UserDetails userDetails, Employee emp)
    {

        return Jwts.builder().subject(userDetails.getUsername()).claim("empid", emp.getEmpid()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SECRETKEY, Jwts.SIG.HS256).compact();

    }
    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 days
                .signWith(SECRETKEY, Jwts.SIG.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(SECRETKEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public Long extractEmpId(String token)
    {
        return Jwts.parser().verifyWith(SECRETKEY).build()
                .parseSignedClaims(token)
                .getPayload()
                .get("empid",Long.class);
    }
    public String generateRefreshToken(UserDetails userDetails, Employee employee) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("empid", employee.getEmpid())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 days
                .signWith(SECRETKEY, Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(SECRETKEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

}
