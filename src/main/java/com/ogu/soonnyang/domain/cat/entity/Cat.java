package com.ogu.soonnyang.domain.cat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ogu.soonnyang.domain.cat.dto.CreateCatRequest;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private CatGender gender;
    private Long followerCnt;
    private String imageUrl;
    @Column(name = "is_active", nullable = false)
    @ColumnDefault("'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private CatState isActive;

//    @ApiModelProperty(value = "중성화 시행 년월일", example = "2024-04-01")
    private LocalDate TNRDate;

//    @Builder
//    public Cat(CreateCatRequest createCatRequest, String imageUrl) {
//        this.name = createCatRequest.getName();
//        this.age = createCatRequest.getAge();
//        this.gender = createCatRequest.getGender();
//        this.followerCnt = 0L;
//        this.imageUrl = imageUrl;
//        this.isActive = CatState.ACTIVE;
//    }

    public void updateFollowCnt(Long followerCnt) {
        this.followerCnt = followerCnt;
    }
}