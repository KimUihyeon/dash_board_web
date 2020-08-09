package com.sutdy.dashboard.domain.todo;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.03
 */
@Table
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String icon;

    private boolean canModify;

    private String iconColor;

    private String fontColor;

    private LocalDateTime cDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Account account;


    public void patch(TodoCategoryDto dto) {
        if (!dto.getTitle().equals(this.title)) {
            this.title = dto.getTitle();
        }
        if (!dto.getIcon().equals(this.icon)) {
            this.icon = dto.getIcon();
        }
        if (!dto.getFontColor().equals(this.getFontColor())) {
            this.fontColor = dto.getFontColor();
        }
        if (!dto.getIconColor().equals(this.iconColor)) {
            this.iconColor = dto.getIconColor();
        }
        if (!dto.getCDate().equals(this.cDate)) {
            this.cDate = DateUtil.stringToLocalDateTime(dto.getCDate(), ApplicationStringConfig.DATE_FORMAT);
        }
        if (dto.isCanModify() != this.canModify) {
            this.canModify = dto.isCanModify();
        }
    }

}
