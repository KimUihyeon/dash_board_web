package com.sutdy.dashboard.setting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * RequestLoop
 *
 * @author Kimuihyeon
 * @since 2020.11.10
 */

public class RequestAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(RequestAnalysis.class);

    /**
     * 해당 request에 묻어온 Header 다 찍어줌
     *
     * @param request HttpServletRequest
     */
    public static void headerFullLog(HttpServletRequest request) {
        Enumeration datas = request.getHeaderNames();

        while (datas.hasMoreElements()) {
            String key = datas.nextElement().toString();
            headerLog(request, key);
        }
    }

    /**
     * 해당 request에 묻어온 Header의 Header이름으로 탐색해서 로그에 찍어줌
     *
     * @param request    HttpServletRequest
     * @param headerName headerParameter Name
     */
    public static void headerLog(HttpServletRequest request, String headerName) {
        String value = request.getHeader(headerName);
        logger.info("key : " + headerName + "\t||\tvalue: " + value);
    }
}
