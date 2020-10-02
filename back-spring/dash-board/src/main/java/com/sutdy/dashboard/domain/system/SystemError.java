package com.sutdy.dashboard.domain.system;

import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * @author kuh
 * @since 2020.05.28
 */

@Table
@Entity
@Builder
@Getter
@AllArgsConstructor
public class SystemError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp cDate;

    private String errorMessage;

    /**
     * error가 발생한 유저
     */
    private String userId;


    /**
     * 발생한 애러
     */
    private String error;


    /**
     * error가 발생한 파일명
     * ex) "SystemErrorServiceTest.java"
     */
    private String fileName;


    /**
     * error가 발생한 패키지
     * ex) "com.sutdy.dashboard.service.SystemErrorServiceTest"
     */
    private String declaringClass;

    /**
     * error가 발생한 메서드
     * ex) saveTest
     */
    private String methodName;


    /**
     * error가 발생한 라인수
     */
    private int lineNumber;

    public SystemError(){
        this.cDate = DateUtil.now();
    }

    @Override
    public String toString() {
        return "SystemError{" +
                "id=" + id +
                ", cDate=" + cDate +
                ", userId='" + userId + '\'' +
                ", error='" + error + '\'' +
                ", fileName='" + fileName + '\'' +
                ", declaringClass='" + declaringClass + '\'' +
                ", methodName='" + methodName + '\'' +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
