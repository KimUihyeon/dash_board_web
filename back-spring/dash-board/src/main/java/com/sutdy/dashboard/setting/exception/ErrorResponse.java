package com.sutdy.dashboard.setting.exception;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.28
 */

@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int httpStatus;

    private String msg;

    private String date;


    public ErrorResponse(){
        this.date = DateUtil.now(ApplicationStringConfig.DATE_FORMAT);
    }
}
