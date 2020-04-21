package com.minal.scheduler.utils;

import com.minal.scheduler.dao.EmployeeDao;
import com.minal.scheduler.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TokenAuth {
    private static final Key key = MacProvider.generateKey();
    private static final SignatureAlgorithm signatureAlgo = SignatureAlgorithm.HS512;
    private static final long ttl = TimeUnit.DAYS.toMillis(48);

    public static String generateToken(Employee employee) {
        final long nowMills = System.currentTimeMillis();
        final long expTime = nowMills + ttl;
        return Jwts.builder().setSubject(String.valueOf(employee.getId()))
                .signWith(signatureAlgo, key)
                .setIssuedAt(new Date(nowMills))
                .setExpiration(new Date(expTime))
                .compact();
    }
    public static Employee getEmployeeFromToken(String jwt) {
        try {

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            final int userId = Integer.parseInt(claims.getSubject());
            EmployeeDao employeeDao = new EmployeeDao();
            return employeeDao.getEmployee(userId);
        } catch (SignatureException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
