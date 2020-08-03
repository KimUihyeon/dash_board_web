package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.common.ToConverter;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.06
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements ToConverter<Account, AccountDto> {

    private String id;

    private String pw;

    private String name;

    private LocalDateTime cDate;

    public AccountDto(Account account){
        of(account);
    }

    @Override
    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .pw(this.pw)
                .cDate(this.cDate)
                .name(this.name)
                .build();
    }

    @Override
    public AccountDto of(Account account) {
        PropertyMap<Account, AccountDto> map = new PropertyMap<Account, AccountDto>() {
            @Override
            protected void configure() {

            }
        };

        return ModelConverter.map(map, account, AccountDto.class);
    }
}
