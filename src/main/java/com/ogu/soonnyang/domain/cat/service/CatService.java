package com.ogu.soonnyang.domain.cat.service;

import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.dto.CatInfoRequest;
import com.ogu.soonnyang.domain.cat.dto.CatListResponse;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.repository.CatCustomRepository;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {
    private final Logger LOGGER = LoggerFactory.getLogger(CatService.class);
    private final CatRepository catRepository;
    private final CatCustomRepository catCustomRepository;

    /* POST) 고양이 생성 */
    public Long save(Long memberId, CatInfoRequest catInfoReq) {
        // S3에 이미지 등록
        MultipartFile multipartFile = catInfoReq.getImage();
        String imgUrl = "https://blog.kakaocdn.net/dn/bBkc9z/btqGTm2me0v/qj8u2JvrqgVn3ZFnuK2oKk/img.jpg";

//        try {
//            imgUrl = s3Uploader.upload(multipartFile, "cat");
//        }
//        catch (IOException e) {
//            throw new IllegalArgumentException("파일 업로드에 실패했습니다 : cat upload failed");
//        }
        LOGGER.info("================image url===============\n" + imgUrl);

        Cat cat = new Cat(catInfoReq, imgUrl);
        catRepository.save(cat);

        return cat.getCatId();
    }

    /* GET) 고양이 리스트 조회 */
    public List<CatListResponse> getCats() {
        List<Cat> cats =catRepository.findAll();

        List<CatListResponse> catListResponses = new ArrayList<>();

        for(Cat cat : cats) {
            CatListResponse catResponse = CatListResponse.from(cat);
            catListResponses.add(catResponse);
        }

        return catListResponses;
    }

    /* GET) 고양이 상세 조회 */
    public CatDetailResponse getCat(Long catId) {
        Cat cat = catRepository.findCatByCatId(catId)
                .orElseThrow(() -> new IllegalArgumentException());


        return CatDetailResponse.from(cat);
    }
}