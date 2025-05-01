package com.ntt.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("clave-ntt-test-firma-4321clave-ntt-test-firma-4321".getBytes());

    public static String generarToken(String correo) {
        long tiempoExpiracion = 1000 * 60 * 60 * 10; // 10 horas

        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
