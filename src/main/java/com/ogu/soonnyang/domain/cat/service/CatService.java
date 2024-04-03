package com.ogu.soonnyang.domain.cat.service;

import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.dto.CatListResponse;
import com.ogu.soonnyang.domain.cat.dto.CatRequest;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import com.ogu.soonnyang.domain.cat.repository.CatCustomRepository;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {
    private final Logger LOGGER = LoggerFactory.getLogger(CatService.class);
    private final CatFactory catFactory;
    private final CatRepository catRepository;
    private final CatCustomRepository catCustomRepository;

    /* POST) 고양이 생성 */
    @Transactional
    public Long save(Long memberId, CatRequest catRequest, MultipartFile image) {
        // S3에 이미지 등록
        String imgUrl = "https://blog.kakaocdn.net/dn/bBkc9z/btqGTm2me0v/qj8u2JvrqgVn3ZFnuK2oKk/img.jpg";

//        try {
//            if (!image.isEmpty()) {
//                imgUrl = s3Uploader.upload(multipartFile, "cat");
//            }
//        } catch (IOException e) {
//            throw new IllegalArgumentException("파일 업로드에 실패했습니다 : cat upload failed");
//        }
        LOGGER.info("================image url===============\n" + imgUrl);
        // 멤버 유효성 검사
        Cat cat = catFactory.save(catRequest, imgUrl);

        return cat.getCatId();
    }

    /* GET) 고양이 리스트 조회 */
    @Transactional(readOnly = true)
    public List<CatListResponse> getCats(Long memberId) {
//        List<Cat> cats = catRepository.findAll();
        List<Cat> cats = catRepository.findCatByIsActive(CatState.ACTIVE);
//        catCustomRepository.getCats(memberId);
//        return catRepository.findAll().stream()
//                .map(CatListResponse::from).toList();
        return catRepository.findCatByIsActive(CatState.ACTIVE).stream()
                .map(CatListResponse::from).toList();
    }

    /* GET) 고양이 상세 조회 */
    @Transactional(readOnly = true)
    public CatDetailResponse getCat(Long catId) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with id: " + catId));

        return CatDetailResponse.from(cat);
    }

    @Transactional
    public void updateCat(Long catId, CatRequest catRequest, MultipartFile image) {
        // S3에 이미지 등록

        String imgUrl = "https://cdn.dailycc.net/news/photo/202307/749409_650699_5735.jpg";

//        try {
//            if (!image.isEmpty()) {
//                imgUrl = s3Uploader.upload(multipartFile, "cat");
//            }
//        } catch (IOException e) {
//            throw new IllegalArgumentException("파일 업로드에 실패했습니다 : cat upload failed");
//        }
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("Thesis not found with Id : " + catId));
        cat.updateCat(catRequest, imgUrl);
    }

    @Transactional
    public void deleteCat(Long catId) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("Thesis not found with Id : " + catId));
        cat.deleteCat(catId);
    }
}