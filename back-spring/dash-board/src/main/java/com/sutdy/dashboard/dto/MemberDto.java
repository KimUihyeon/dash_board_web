package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.members.Member;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.06
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto extends AbsDtoConverter<Member> {

    private String id;

    private String pw;

    private String name;

    private LocalDateTime cDate;

    public MemberDto(Member entity) {
        createDto(entity);
    }

    @Override
    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .pw(this.pw)
                .cDate(this.cDate)
                .name(this.name)
                .build();
    }

    @Override
    public void createDto(Member entity) {
        this.id = entity.getId();
        this.pw = entity.getPw();
        this.name = entity.getName();
        this.cDate = entity.getCDate();
    }
}
