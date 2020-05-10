package com.sutdy.dashboard.setting.util.auth.jwt;

import com.sutdy.dashboard.domain.members.Member;
import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kuh
 * @since 2020.05.10
 */
public class JWT {

    public final static String KEY = "rladmlgusWkdWkdaos";
    public final static String ISS = "dash-board.com";

    public static String create(String userId, String userName, int lifeDate) {

        Map<String, Object> header = new HashMap<>();
        header.put("type", "jwt");
        header.put("alg", "HS256");

        Map<String, Object> claim = new HashMap<>();
        claim.put("iss", ISS);
        claim.put("exp", String.valueOf(calculatorLifeTime(lifeDate)));
        claim.put("userId", userId);
        claim.put("useName", userName);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public static Long calculatorLifeTime(int lifeDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, lifeDate);
        Date date = cal.getTime();

        return date .getTime();
    }

    public static Boolean validation(String jwt) {
        return false;
    }


    public static AuthEnum authJwt(String jwt) {
        Long currentTimeStep = new Date().getTime();

        try {
            Jwt claims = Jwts.parser().setSigningKey(KEY)
                    .parse(jwt);
            Map<String, String> payload = (Map<String, String>) claims.getBody(); // claim
            claims.getHeader(); // header

            Long tokenExp = Long.parseLong(payload.get("exp"));
            String tokenIss = payload.get("iss");

            if (tokenExp > currentTimeStep) {
                if (ISS.equals(tokenIss)) {
                    return AuthEnum.Auth;
                } else {
                    return AuthEnum.WrongEncounter;
                }
            } else {
                return AuthEnum.TimeOut;
            }

        } catch (Exception e) {
            return AuthEnum.WrongEncounter;
        }
    }
}
