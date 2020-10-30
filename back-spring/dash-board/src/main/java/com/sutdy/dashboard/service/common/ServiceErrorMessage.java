package com.sutdy.dashboard.service.common;

import com.sutdy.dashboard.setting.PropertyFileManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kuh
 * @since 2020.04.17
 */

@PropertySource(value = PropertyFileManager.ERROR_MGS_PROP, encoding = "utf-8")
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

    @Value("${not_implement_mhthod}")
    protected String NOT_IMPLEMENT_METHOD;

    @Value("${not_access}")
    protected String NOT_ACCESS;
}
