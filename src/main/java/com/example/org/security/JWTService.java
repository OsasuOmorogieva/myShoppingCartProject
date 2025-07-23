package com.example.org.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.org.Provider.ResourceProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
@Autowired
 ResourceProvider resourceProvider;
	
	
	
//	public JWTService(ResourceProvider resourceProvider) {
//		this.resourceProvider = resourceProvider;
//	}
//	
//	public JWTService() {
//		KeyGenerator keyGen;
//		try {
//			keyGen = KeyGenerator.getInstance("HmacSHA256");
//			SecretKey sk = keyGen.generateKey();
//			secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
//		
//	}

//	public String generateToken(String username) {
//		Map<String, Object> claims = new HashMap<>();
//		return Jwts.builder()
//				.claims()
//				.add(claims)
//				.subject(username)
//				.issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis()+ 1000*60*30))
//				.and()
//				.signWith(getKey())
//				.compact();
//			
//		
//	}
	public String generateToken(String username) {
	Map<String, Object> claims = new HashMap<>();
	return Jwts.builder()
		    .claims(claims)
		    .subject(username)
		    .issuedAt(new Date(System.currentTimeMillis()))
		    .expiration(new Date(System.currentTimeMillis() + resourceProvider.getJwtExpiration())) // 30 mins
		    .signWith(getKey())
		    .compact();
	}

	private Key getKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode(resourceProvider.getJwtSecret());
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}

	private <T>T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith((SecretKey) getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		
		return (userName.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}
	
	

}
