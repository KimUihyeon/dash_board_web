package com.sutdy.dashboard.setting.util.auth.jwt;

import com.sutdy.dashboard.service.MemberService;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kuh
 * @since 2020.05.10
 */
public class JWT {

    @Autowired
    private MemberService memberService;


    private final static String TOKENT_PREFIX = "bearer";
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

        return TOKENT_PREFIX + " " + Jwts.builder()
                .setHeader(header)
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    private static Long calculatorLifeTime(int lifeDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, lifeDate);
        Date date = cal.getTime();

        return date .getTime();
    }

    public static Boolean validation(String jwt) {
        return false;
    }


    public static AuthResponse authJwt(String jwt) {
        Long currentTimeStep = new Date().getTime();

        try {
            jwt= jwt.replaceAll(TOKENT_PREFIX+" ","");
            Jwt claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parse(jwt);

            Map<String, String> payload = (Map<String, String>) claims.getBody(); // claim

            Long tokenExp = Long.parseLong(payload.get("exp"));

            AuthResponse response = AuthResponse.builder()
                    .token(jwt)
                    .IIS(ISS)
                    .id(payload.get("userId"))
                    .name(payload.get("userName"))
                    .authEndDate(
                            new SimpleDateFormat(ApplicationStringConfig.DATE_FORMAT)
                            .format(
                                    new Date(tokenExp)
                            )
                    )
                    .build();

            if (tokenExp > currentTimeStep) {
                if (ISS.equals(payload.get("iss")) ) {
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
            return AuthResponse.builder()
                    .authType(AuthEnum.WrongEncounter)
                    .build();
        }
    }
}
