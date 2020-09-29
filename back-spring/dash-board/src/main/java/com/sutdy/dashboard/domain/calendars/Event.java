package com.sutdy.dashboard.domain.calendars;

import com.sutdy.dashboard.dto.EventDto;
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
    @ManyToOne
    @JoinColumn(name = "calendarId")
    private Calendar calendar;

    public void patch(EventDto dto) {

    }
}
