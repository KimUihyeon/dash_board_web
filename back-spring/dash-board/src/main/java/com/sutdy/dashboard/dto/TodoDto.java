package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto extends AbsDtoConverter<Todo> {

    private Long id;

    private String title;

    private String memo;

    private String date;

    private boolean todoComplete;

    private boolean toDay; // 오늘 할일

    private boolean isImportant; // 중요

    private String sDate;   // 스케줄 시작일

    private String eDate;   // 스케줄 종료일

    private Long categoryId;

    private String categoryName;

    public TodoDto(Todo entity) {
        this.createDto(entity);
    }

    @Override
    public Todo toEntity() {
        String dateFormat = ApplicationStringConfig.DATE_FORMAT;

        return Todo.builder()
                .id(this.id)
                .cDate(DateUtil.stringToLocalDateTime(this.date, dateFormat))
                .complete(this.todoComplete)
                .contents(this.memo)
                .title(this.title)
                .isImportant(this.isImportant)
                .toDay(this.toDay)
                .sDate(DateUtil.stringToLocalDateTime(this.sDate, dateFormat))
                .eDate(DateUtil.stringToLocalDateTime(this.eDate, dateFormat))
                .build();
    }

    @Override
    public void createDto(Todo entity) {
        String dateFormat = ApplicationStringConfig.DATE_FORMAT;
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.todoComplete = entity.isComplete();
        this.date = entity.getCDate() == null ? null : DateUtil.localDateTimeToString(entity.getCDate() ,dateFormat );
        this.memo = entity.getContents();
        this.isImportant = entity.isImportant();
        this.toDay = entity.isToDay();
        this.categoryName = entity.getTodoCategory() == null ? null : entity.getTodoCategory().getTitle();
        this.categoryId = entity.getTodoCategory() == null ? null : entity.getTodoCategory().getId();
        this.eDate = entity.getEDate() == null ? null : DateUtil.localDateTimeToString(entity.getEDate() ,dateFormat );
        this.sDate = entity.getSDate() == null ? null : DateUtil.localDateTimeToString(entity.getSDate() ,dateFormat );
    }

}
