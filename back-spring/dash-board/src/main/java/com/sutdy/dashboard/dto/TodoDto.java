package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

import java.sql.Timestamp;


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

    private boolean todoComplete;

    private boolean toDay; // 오늘 할일

    private boolean isImportant; // 중요

    private String cDate;

    private String sDate;   // 스케줄 시작일

    private String eDate;   // 스케줄 종료일

    private Long categoryId;

    private String categoryName;


    @Override
    public Todo toEntity() {
        String dateFormat = ApplicationStringConfig.DATE_FORMAT;

        return Todo.builder()
                .id(this.id)
                .complete(this.todoComplete)
                .contents(this.memo)
                .title(this.title)
                .isImportant(this.isImportant)
                .toDay(this.toDay)
                .cDate(DateUtil.toTimeStamp(this.cDate, dateFormat))
                .sDate(DateUtil.toTimeStamp(this.sDate, dateFormat))
                .eDate(DateUtil.toTimeStamp(this.eDate, dateFormat))
                .build();
    }

    @Override
    public TodoDto of(Todo todo) {

        PropertyMap<Todo , TodoDto> propertyMap = new PropertyMap<Todo, TodoDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTitle(source.getTitle());
                map().setMemo(source.getContents());
                map().setTodoComplete(source.isComplete());
                map().setToDay(source.isToDay());
                map().setImportant(source.isImportant());
                map().setSDate(
                        DateUtil.toString(
                                source.getSDate(), ApplicationStringConfig.DATE_FORMAT)
                );
                map().setEDate(
                        DateUtil.toString(
                                source.getEDate(), ApplicationStringConfig.DATE_FORMAT)
                );
                map().setCDate(
                        DateUtil.toString(
                                source.getCDate(), ApplicationStringConfig.DATE_FORMAT)
                );
            }
        };

        TodoDto dto = ModelConverter.map(propertyMap, todo , TodoDto.class);
        dto.setSDate(
                DateUtil.toString(
                        todo.getSDate(), ApplicationStringConfig.DATE_FORMAT)
        );
        dto.setEDate(
                DateUtil.toString(
                        todo.getEDate(), ApplicationStringConfig.DATE_FORMAT)
        );
        dto.setCDate(
                DateUtil.toString(
                        todo.getCDate(), ApplicationStringConfig.DATE_FORMAT)
        );
        return dto;
    }
}
