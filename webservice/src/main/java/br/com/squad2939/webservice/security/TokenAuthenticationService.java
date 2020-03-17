package br.com.squad2939.webservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    static final Long EXPIRATION_TIME = 860000000L; // 10 dias
    static final String SECRET = "aab72c8d87ee011829123e7591a74c08";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse response, Long id) {
        String JWT = Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        String user = validate(token);

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }

        return null;
    }

    static Long getUserIdFromToken(String token) {
        String user = validate(token);
        return user != null ? Long.valueOf(user) : null;
    }

    static String validate(String token) {
        if (token == null)
            return null;

        if (!token.contains(TOKEN_PREFIX))
            return null;

        token = token.replace(TOKEN_PREFIX, "").trim();

        if (!token.equals("")) {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
        }

        return null;
    }
}
