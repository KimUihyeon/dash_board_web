package com.sutdy.dashboard.domain.todo;

import com.sutdy.dashboard.dto.TodoDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String contents;

    private LocalDateTime cDate;

    private boolean complete; // 완료 유무

    private boolean toDay; // 오늘 할일

    private boolean isImportant; // 중요

    @Setter
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private TodoCategory todoCategory;


    public void patch(TodoDto dto) {

        if (dto.getMemo() != null && !dto.getMemo().equals(this.contents)) {
            this.contents = dto.getMemo();
        }
        if (dto.getTitle() != null && !dto.getTitle().equals(this.title)) {
            this.contents = dto.getTitle();
        }
        if (dto.isTodoComplete() != this.complete) {
            this.complete = dto.isTodoComplete();
        }
        if (dto.isImportant() != this.isImportant) {
            this.isImportant = dto.isImportant();
        }
        if (dto.isToDay() != this.toDay) {
            this.toDay = dto.isToDay();
        }



    }
}
