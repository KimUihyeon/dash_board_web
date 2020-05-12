package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.Util;
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


    private Long categoryId;

    private String categoryName;

    public TodoDto(Todo entity) {
        this.createDto(entity);
    }

    @Override
    public Todo toEntity() {

        return Todo.builder()
                .id(this.id)
                .cDate(Util.stringToLocalDateTime(this.date, ApplicationStringConfig.DATE_FORMAT))
                .complete(this.todoComplete)
                .contents(this.memo)
                .title(this.title)
                .isImportant(this.isImportant)
                .toDay(this.toDay)
                .build();
    }

    @Override
    public void createDto(Todo entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.todoComplete = entity.isComplete();
        this.date = entity.getCDate() == null ? null : Util.localDateTimeToString(entity.getCDate() , ApplicationStringConfig.DATE_FORMAT);
        this.memo = entity.getContents();
        this.isImportant = entity.isImportant();
        this.toDay = entity.isToDay();
        this.categoryName = entity.getTodoCategory() == null ? null : entity.getTodoCategory().getTitle();
        this.categoryId = entity.getTodoCategory() == null ? null : entity.getTodoCategory().getId();
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", memo='" + memo + '\'' +
                ", date=" + date +
                ", todoComplete=" + todoComplete +
                '}';
    }
}
