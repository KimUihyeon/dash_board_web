package com.sutdy.dashboard.domain.calendars;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.TaskTagDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "taskTag")
public class TaskTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String color; // HexColor 예)#fff
    private LocalDateTime cDate;

    @Setter
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @Setter
    @OneToMany(mappedBy = "taskTag")
    private List<Task> task;


    public void patch(TaskTagDto dto){

    }

}
