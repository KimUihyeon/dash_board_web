package com.sutdy.dashboard.domain.calendars;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.CalendarDto;
import lombok.*;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
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
@Table( name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String color; // HexColor 예)#fff
    private Timestamp cDate;

    private boolean checked;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

//    @Setter
//    @JoinColumn(name = "eventId")
    @OneToMany(mappedBy = "calendar")
    private List<Event> events = new ArrayList<>();

    public void add(Event event){
        this.events.add(event);
        event.setCalendar(this);
    }

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
        if(dto.isChecked() != this.checked){
            this.checked = dto.isChecked();
        }
    }

}
