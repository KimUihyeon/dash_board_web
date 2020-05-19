package com.sutdy.dashboard.setting.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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
     * 필터데이터
     */
    private Map<String, Object> filterDetail;

    /**
     * 현재 페이지
     * default = -1
     */
    private int currentPage = -1;

    /**
     *
     */
    private int size = 20;


    public SearchParams(){
        this.filterDetail = new HashMap<String, Object>();
    }
}
