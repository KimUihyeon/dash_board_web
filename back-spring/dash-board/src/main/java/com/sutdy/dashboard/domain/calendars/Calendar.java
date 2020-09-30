package com.sutdy.dashboard.domain.calendars;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.CalendarDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String color; // HexColor 예)#fff
    private LocalDateTime cDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar", cascade = CascadeType.ALL)
//    @JoinColumn(name = "eventId")
    private List<Event> event;

    public void patch(CalendarDto dto) {
        if (dto.getTitle() != null && !dto.getTitle().equals(this.title)) {
            this.title = dto.getTitle();
        }
        if (dto.getDescription() != null && !dto.getDescription().equals(this.description)) {
            this.description = dto.getDescription();
        }
        if (dto.getColor() != null && !dto.getColor().equals(this.color)) {
            this.color = dto.getColor();
        }
    }

}
