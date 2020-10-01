package com.sutdy.dashboard.domain.system;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.28
 */

@Table
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SystemError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime cDate = LocalDateTime.now();

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
