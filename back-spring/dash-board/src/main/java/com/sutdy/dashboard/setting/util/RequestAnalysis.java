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

public class RequestLoop {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoop.class);

    public static void headerLoop(HttpServletRequest request){

        Enumeration datas = request.getHeaderNames();
        logger.info(request.getRequestURI());

        while (datas.hasMoreElements()) {
            String key = datas.nextElement().toString();
            String value = request.getHeader(key);
            logger.info("key : " + key + "\t||\tvalue: " + value);
        }
    }
}
