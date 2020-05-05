package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime date;

    private boolean todoComplete;

    public TodoDto(Todo entity) {
        this.createDto(entity);
    }

    @Override
    public Todo toEntity() {
        return Todo.builder()
                .id(this.id)
                .cDate(this.date)
                .complete(this.todoComplete)
                .contents(this.memo)
                .title(this.title)
                .build();
    }

    @Override
    public void createDto(Todo entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.todoComplete = entity.isComplete();
        this.date = entity.getCDate();
        this.memo = entity.getContents();
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
