package com.sutdy.dashboard.setting;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.08.20
 */
@Component
public class ProjectSetup implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProjectSetup.class);

    @Autowired
    private AccountService accountService;

    private static short pipelineNumber = 0 ;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        logger.info("---------------------------------------------");
        logger.info("---------------------------------------------");
        logger.info("-------------- Application Ready ------------");
        logger.info("* setup Pipeline");


        accountDefaultData();


        logger.info("-------------- Application Ready ------------");
        logger.info("---------------------------------------------");
        logger.info("---------------------------------------------");
    }


    /**
     * Account AdminData
     */
    private void accountDefaultData(){
        pipelineNumber++;
        logger.info("\t"+ pipelineNumber +". Account default data");

        AccountDto account = AccountDto.builder()
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .id("admin@admin.com")
                .pw("12341234")
                .name("운영자")
                .build();

        if(this.accountService.findById(account.getId()) == null){
            try{
                this.accountService.save(account);
                logger.info("\t\t - account data 생성 (" + account.getId() + ")" );
            }catch (Exception e){
                e.printStackTrace();
                logger.info("\t\t - account data 생성 실패");
            }
        }else {
            logger.info("\t\t - account data 이미 있음");
        };
    }


}