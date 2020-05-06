package com.sutdy.dashboard.domain.members;

import com.sutdy.dashboard.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.06
 */

@Table
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private String id;

    private String pw;

    private String name;

    private LocalDateTime cDate;

    private boolean use;

    public void patch(MemberDto dto){
        if(!dto.getCDate().equals(this.cDate)){
            this.cDate = dto.getCDate();
        }

        if(!dto.getName().equals(this.name)) {
            this.name = dto.getName();
        }

        if(!dto.getPw().equals(this.name)){
            this.pw = dto.getPw();
        }

    }
}
