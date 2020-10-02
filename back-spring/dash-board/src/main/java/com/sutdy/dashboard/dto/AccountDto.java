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
import org.modelmapper.PropertyMap;

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

    private String dDate;

    @Override
    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .pw(this.pw)
                .name(this.name)
                .cDate(
                        DateUtil.toTimeStamp(this.cDate, ApplicationStringConfig.DATE_FORMAT)
                )
                .dDate(
                        DateUtil.toTimeStamp(this.dDate, ApplicationStringConfig.DATE_FORMAT)
                )
                .build();
    }

    @Override
    public AccountDto of(Account account) {

        PropertyMap<Account, AccountDto> map = new PropertyMap<Account, AccountDto>() {
            @Override
            protected void configure() {
                map().setPw(source.getPw());
                map().setId(source.getId());
                map().setName(source.getName());
                if(source.getCDate() != null){
                    map().setCDate(DateUtil.toString(source.getCDate(), ApplicationStringConfig.DATE_FORMAT));
                }
                if(source.getDDate() != null){
                    map().setDDate(DateUtil.toString(source.getDDate(), ApplicationStringConfig.DATE_FORMAT));
                }
            }
        };

        AccountDto dto = ModelConverter.map(map, account, AccountDto.class);
        dto.setCDate(DateUtil.toString(account.getCDate(), ApplicationStringConfig.DATE_FORMAT));
        dto.setDDate(DateUtil.toString(account.getDDate(), ApplicationStringConfig.DATE_FORMAT));
        return dto;
    }
}
