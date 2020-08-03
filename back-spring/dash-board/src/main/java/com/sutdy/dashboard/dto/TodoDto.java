package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.dto.common.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto implements ToConverter<Todo, TodoDto> {

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


    public TodoDto(Todo todo){
        of(todo);
    }

    @Override
    public Todo toEntity() {
        String dateFormat = ApplicationStringConfig.DATE_FORMAT;

        LocalDateTime cDate = this.date == null ? null : DateUtil.stringToLocalDateTime(this.date, dateFormat);
        LocalDateTime sDate = this.sDate == null ? null : DateUtil.stringToLocalDateTime(this.sDate, dateFormat);
        LocalDateTime eDate = this.eDate == null ? null : DateUtil.stringToLocalDateTime(this.eDate, dateFormat);

        return Todo.builder()
                .id(this.id)
                .cDate(cDate)
                .complete(this.todoComplete)
                .contents(this.memo)
                .title(this.title)
                .isImportant(this.isImportant)
                .toDay(this.toDay)
                .sDate(sDate)
                .eDate(eDate)
                .build();
    }

    @Override
    public TodoDto of(Todo todo) {

        PropertyMap<Todo , TodoDto> propertyMap = new PropertyMap<Todo, TodoDto>() {
            @Override
            protected void configure() {
                map().setSDate(
                        DateUtil.localDateTimeToString(
                                source.getSDate(), ApplicationStringConfig.DATE_FORMAT)
                );

                map().setEDate(
                        DateUtil.localDateTimeToString(
                                source.getEDate(), ApplicationStringConfig.DATE_FORMAT)
                );

                map().setDate(
                        DateUtil.localDateTimeToString(
                                source.getCDate(), ApplicationStringConfig.DATE_FORMAT)
                );
                map().setId(source.getId());
                map().setTitle(source.getTitle());
                map().setMemo(source.getContents());
                map().setTodoComplete(source.isComplete());
                map().setToDay(source.isToDay());
                map().setImportant(source.isImportant());
            }
        };
        return ModelConverter.map(propertyMap, todo , TodoDto.class);
    }
}
