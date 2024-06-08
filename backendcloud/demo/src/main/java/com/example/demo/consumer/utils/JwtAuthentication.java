package com.example.demo.consumer.utils;

import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import net.sf.jsqlparser.statement.create.table.CreateTable;

public class JwtAuthentication {
    public static Integer getUserId(String token){

        int userId = -1;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}
