package info.kaminosoft.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {
    
     // Frase secreta para firmar el token (debe ser larga y segura)
     private static final String SECRET_KEY =  Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "SECRET_KEY_JWT");

     // Tiempo de expiración del token (en milisegundos, por ejemplo, 1 hora)
     private static final String EXPIRATION_TIME = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "TIME_EXPIRE_TOKEN"); // 1 hora
 
   
     public static String generateToken(String subject) {
       
         SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
         long expirationTime = Long.parseLong(EXPIRATION_TIME);
         Date now = new Date();
         Date expiryDate = new Date(now.getTime() + expirationTime);
         return Jwts.builder()
                 .subject(subject)
                 .expiration(expiryDate)
                 .signWith(secretKey)
                 .compact();
     }
 
     public static Claims getSubjectFromToken(String token) throws Exception {
         SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
 
         return Jwts.parser()
                 .verifyWith(secretKey)
                 .build()
                 .parseSignedClaims(token) // Parsear el token
                 .getPayload();           // Obtener los datos del token
     }
     
     public static Claims validateToken(String token) {
    	 SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
       Claims claims = null;
	    try {
	      claims=Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	      return claims;
	    } catch (Exception e) {
	      return null;
	    }
	  }
 
     public static void main(String[] args) {
         try {
             // Generar un token
             String token = JwtUtil.generateToken("usuario123");
             System.out.println("Token generado: " + token);
 
             // Validar el token
             Claims claims = JwtUtil.getSubjectFromToken(token);
             System.out.println("Token válido:");
             System.out.println("Subject: " + claims.getSubject());
             System.out.println("Issuer: " + claims.getIssuer());
             System.out.println("Issued At: " + claims.getIssuedAt());
             System.out.println("Expiration: " + claims.getExpiration());
         } catch (Exception e) {
             System.err.println("Error: " + e.getMessage());
         }
     }
}
