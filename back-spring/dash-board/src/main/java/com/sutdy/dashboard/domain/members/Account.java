package com.sutdy.dashboard.domain.members;

import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * @author kuh
 * @since 2020.05.06
 */

@Table
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String id;

    private String pw;

    private String name;

    private Timestamp cDate;

    private Timestamp dDate;

    public void patch(AccountDto dto) {
        if (dto.getCDate() != null && !DateUtil.compare(dto.getCDate(), this.cDate, ApplicationStringConfig.DATE_FORMAT)) {
            this.cDate = DateUtil.toTimeStamp(dto.getCDate(), ApplicationStringConfig.DATE_FORMAT);
        }

        if (dto.getDDate() != null && !DateUtil.compare(dto.getDDate(), this.dDate, ApplicationStringConfig.DATE_FORMAT)) {
            this.dDate = DateUtil.toTimeStamp(dto.getDDate(), ApplicationStringConfig.DATE_FORMAT);
        }

        if (!dto.getName().equals(this.name)) {
            this.name = dto.getName();
        }

        if (!dto.getPw().equals(this.name)) {
            this.pw = dto.getPw();
        }

    }
}
