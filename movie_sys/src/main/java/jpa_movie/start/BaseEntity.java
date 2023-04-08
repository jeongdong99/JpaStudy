package jpa_movie.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity { // 공통 매핑 정보를 모아놓은 추상 클래스
    //영화 생성 or 고객 생성
    private String createdBy;

    //생성일
    private LocalDateTime createdDate;

    //영화 수정 or 고객 수정
    private String lastModifiedBy;

    //수정일
    private LocalDateTime lastModifiedDate;

}
