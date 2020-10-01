package com.sutdy.dashboard.domain.calendars;

import com.sutdy.dashboard.dto.EventDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String icon;
    private String context;

    private LocalDateTime cDate; // 생성
    private LocalDateTime uDate; // 업데이트
    private LocalDateTime sDate; // 시작일
    private LocalDateTime eDate; // 종료일

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId")
    private Calendar calendar;

    public void patch(EventDto dto) {
        if (!this.title.equals(dto.getTitle())) {
            this.title = dto.getTitle();
        }
        if (!this.icon.equals(dto.getIcon())) {
            this.title = dto.getIcon();
        }
        if (!this.context.equals(dto.getContext())) {
            this.context = dto.getContext();
        }
        if (!DateUtil.dateTimeCompare(dto.getSDate(), this.sDate, ApplicationStringConfig.DATE_FORMAT)) {
            this.sDate = DateUtil.stringToLocalDateTime(dto.getSDate(), ApplicationStringConfig.DATE_FORMAT);
        }
        if (!DateUtil.dateTimeCompare(dto.getEDate(), this.sDate, ApplicationStringConfig.DATE_FORMAT)) {
            this.eDate = DateUtil.stringToLocalDateTime(dto.getEDate(), ApplicationStringConfig.DATE_FORMAT);
        }
        this.uDate = DateUtil.now();
    }
}
