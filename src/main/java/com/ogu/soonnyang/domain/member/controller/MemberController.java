package com.ogu.soonnyang.domain.member.controller;

import com.ogu.soonnyang.domain.auth.controller.AuthController;
import com.ogu.soonnyang.domain.auth.jwt.JwtTokenProvider;
import com.ogu.soonnyang.domain.member.dto.MemberDetailResponse;
import com.ogu.soonnyang.domain.member.dto.request.UpdateMemberRequest;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.member.service.SignService;
import com.ogu.soonnyang.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Tag(name = "Members", description = "Members 관련 API 입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final SignService signService;
    //    private final FollowService followService;
    private final TokenUtils tokenUtils;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMember(HttpServletRequest request) {
        Claims claims = tokenUtils.getClaimsFromRequest(request);
        Long memberId = (Long) claims.get("member_id");
        String email = claims.get("email").toString();
        String nickname = claims.get("nickname").toString();

        Member member = signService.getMember(memberId);
        LOGGER.info("토큰에서 꺼낸 닉네임 : {}", nickname);

        return ResponseEntity.ok(
                MemberDetailResponse.from(member)
        );
    }

    @PutMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> updateMember(HttpServletRequest request, @RequestBody UpdateMemberRequest form) {
        Claims claims = tokenUtils.getClaimsFromRequest(request);
        Long memberId = Long.valueOf(claims.get("member_id").toString());
        String email = claims.get("email").toString();
        signService.updateMember(memberId, form);
        String token = jwtTokenProvider.createToken(email);

        Map<String, String> map = new HashMap<>();
        map.put("msg", "회원정보(닉네임)가 수정되었습니다.");
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

    /*
    @GetMapping("/follow-list")
    public ResponseEntity<?> getFollows(HttpServletRequest request) {
        Claims claims = tokenUtils.getClaimsFromRequest(request);
        UUID memberId = UUID.fromString(claims.get("member_id").toString());
        List<CatListResp> follows = followService.getFollows(memberId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("cats", follows);
        return ResponseEntity.ok(resultMap);
    }

     */
}
