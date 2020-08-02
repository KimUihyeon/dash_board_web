package com.sutdy.dashboard.service.common;

import com.sutdy.dashboard.setting.PropertyFileManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kuh
 * @since 2020.04.17
 */

@PropertySource(PropertyFileManager.ERROR_MGS_PROP)
public abstract class ServiceErrorMessage {

    @Value("${not_find_data}")
    protected String NOT_FIND_DATA;

    @Value("${not_supported_http_method}")
    protected String NOT_SUPPORTED_HTTP_METHOD;

    @Value("${not_find_page}")
    protected String NOT_FIND_PAGE;

    @Value("${not_find_posts}")
    protected String NOT_FIND_POSTS;

    @Value("${fail_delete_jpa}")
    protected String FAIL_DELETE_JPA;

    @Value("구현이 안된 메소드 입니다.")
    protected String NOT_IMPLEMENT_METHOD;
}
