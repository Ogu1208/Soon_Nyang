package com.ogu.soonnyang.domain.cat.entity;

import com.ogu.soonnyang.domain.cat.dto.CatInfoReq;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@Getter
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;
    private String name;
    private Integer age;
    private CatGender gender;
    private Long followerCnt;
    private String image;
    @Column(name = "is_active", nullable = false)
    @ColumnDefault("'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private CatState isActive;

    @Builder
    public Cat(CatInfoReq catInfoReq, String image) {
        this.name = catInfoReq.getName();
        this.age = catInfoReq.getAge();
        this.gender = catInfoReq.getGender();
        this.image = image;
    }

    public void updateFollowCnt(Long followerCnt) {
        this.followerCnt = followerCnt;
    }
}
