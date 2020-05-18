package com.sutdy.dashboard.setting.util.auth.jwt;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.AuthResponseFactory;
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

    private final static String TOKENT_PREFIX = "bearer";

    public static String createToken(String userId, String userName, int lifeDate) {

        Map<String, Object> header = new HashMap<>();
        header.put("type", "jwt");
        header.put("alg", "HS256");

        Map<String, Object> claim = new HashMap<>();
        claim.put("iss", ApplicationStringConfig.WEB_URL);
        claim.put("exp", String.valueOf(calcTokenLifeTime(lifeDate)));
        claim.put("userId", userId);
        claim.put("useName", userName);

        return TOKENT_PREFIX + " " + Jwts.builder()
                .setHeader(header)
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS256, ApplicationStringConfig.JWT_ENCRYPTION_KEY)
                .compact();
    }

    private static Long calcTokenLifeTime(int lifeDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, lifeDate);
        Date date = cal.getTime();
        return date .getTime();
    }


    public static AuthResponse auth(String jwt) {
        Long currentTimeStep = new Date().getTime();

        try {
            jwt= jwt.replaceAll(TOKENT_PREFIX+" ","");
            Jwt claims = Jwts.parser()
                    .setSigningKey(ApplicationStringConfig.JWT_ENCRYPTION_KEY)
                    .parse(jwt);

            Map<String, String> payload = (Map<String, String>) claims.getBody(); // claim

            Long tokenExp = Long.parseLong(payload.get("exp"));

            AuthResponse response = AuthResponseFactory.create(
                    payload.get("userId") , payload.get("userName"),
                    null, jwt, tokenExp );

            if (tokenExp > currentTimeStep) {
                if (ApplicationStringConfig.WEB_URL.equals(payload.get("iss")) ) {
                    response.setAuthType(AuthEnum.Auth);
                    return response;
                } else {
                    response.setAuthType(AuthEnum.WrongEncounter);
                    return response;
                }
            } else {
                response.setAuthType(AuthEnum.TimeOut);
                return response;
            }

        } catch (Exception e) {
            return AuthResponseFactory.create(AuthEnum.WrongEncounter);
        }
    }
}
