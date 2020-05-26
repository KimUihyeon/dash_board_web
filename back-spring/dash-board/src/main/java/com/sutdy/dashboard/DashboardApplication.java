package com.sutdy.dashboard;

import com.sutdy.dashboard.setting.PropertyFileManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(PropertyFileManager.REL_DATA_SOURCE_PROP)
public class DashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

}
