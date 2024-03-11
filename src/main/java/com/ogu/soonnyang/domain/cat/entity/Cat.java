package com.ogu.soonnyang.domain.cat.entity;

import com.ogu.soonnyang.domain.cat.dto.CatInfoRequest;
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
    private String imageUrl;
    @Column(name = "is_active", nullable = false)
    @ColumnDefault("'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private CatState isActive;

    @Builder
    public Cat(CatInfoRequest catInfoRequest, String imageUrl) {
        this.name = catInfoRequest.getName();
        this.age = catInfoRequest.getAge();
        this.gender = catInfoRequest.getGender();
        this.imageUrl = imageUrl;
    }

    public void updateFollowCnt(Long followerCnt) {
        this.followerCnt = followerCnt;
    }
}
