package com.sutdy.dashboard.setting.common;

import lombok.Data;

/**
 * @author kuh
 * @since 2020.05.08
 */
@Data
public class SearchParams {

    /**
     * 필터 명
     */
    private String filter;

    /**
     * 가져올 데이터 id
     */
    private Long id;

    /**
     * 현재 페이지
     * default = -1
     */
    private int currentPage = -1;

    /**
     *
     */
    private int size = 20;
}
