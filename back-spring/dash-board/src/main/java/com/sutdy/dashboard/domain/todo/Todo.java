package com.sutdy.dashboard.domain.todo;

import lombok.Builder;
import lombok.Getter;

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
public class Todo {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String contents;

    private LocalDateTime cDate;

    private boolean complete;
}
