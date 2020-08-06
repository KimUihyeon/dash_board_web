package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String cDate;

    public AccountDto(Account account) {
        of(account);
    }

    @Override
    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .pw(this.pw)
                .name(this.name)
                .cDate(
                        DateUtil.stringToLocalDateTime(this.cDate, ApplicationStringConfig.DATE_FORMAT)
                )
                .build();
    }

    @Override
    public AccountDto of(Account account) {

        AccountDto dto = ModelConverter.map(account, AccountDto.class);
        dto.setCDate(DateUtil.localDateTimeToString(account.getCDate(), ApplicationStringConfig.DATE_FORMAT));
        return dto;
    }
}
