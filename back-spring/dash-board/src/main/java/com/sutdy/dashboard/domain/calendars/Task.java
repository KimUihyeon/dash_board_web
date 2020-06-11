package com.sutdy.dashboard.domain.calendars;

import com.sutdy.dashboard.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String context;

    private LocalDateTime cDate; // 생성
    private LocalDateTime uDate; // 업데이트
    private LocalDateTime sDate; // 시작일
    private LocalDateTime eDate; // 종료일

    @ManyToOne
    @JoinColumn(name = "taskId")
    private TaskTag taskTag;

    public void patch(TaskDto dto) {

    }
}
