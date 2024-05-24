package com.ogu.soonnyang.domain.member.service;

import com.ogu.soonnyang.config.security.common.CommonResponse;
import com.ogu.soonnyang.domain.auth.jwt.JwtTokenProvider;
import com.ogu.soonnyang.domain.member.dto.MemberDTO;
import com.ogu.soonnyang.domain.member.dto.request.SignUpRequest;
import com.ogu.soonnyang.domain.member.dto.request.UpdateMemberRequest;
import com.ogu.soonnyang.domain.member.dto.response.SignInResult;
import com.ogu.soonnyang.domain.member.dto.response.SignUpResult;
import com.ogu.soonnyang.domain.member.entity.Authority;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.member.exception.MemberNotFoundException;
import com.ogu.soonnyang.domain.member.repository.MemberRepository;
import com.ogu.soonnyang.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    //    private final FeedRepository feedRepository;
    private final PostRepository postRepository;
    //    private final DiseaseTimelineRepository diseaseTimelineRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //이메일 중복체크
    @Transactional
    public String checkDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) { //이메일이 있는지?
            return "Y"; //있음 = 중복된 이메일
        } else {
            return "N"; //없음 = 가입 가능한 이메일
        }
    }

    @Transactional
    public void changePasswordByEmail(String email, String newPassword) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with email: " + email));
        member.updatePassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long memberId, UpdateMemberRequest form) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + memberId));
        log.info("[getSignUpResult] 회원 가입 정보 전달");
        member.updateNickname(form.getNickname());
        member.updateIntroduction(form.getIntroduction());

//        memberRepository.save(member);
    }

    @Transactional
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + memberId));
    }

    // 회원 가입
    @Transactional
    public MemberDTO signUp(SignUpRequest form) {
        log.info("[getSignUpResult] 회원 가입 정보 전달");

        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.builder().authorityName("ROLE_USER").build()); // ROLE_USER 권한 부여

        Member member = Member.builder()
                .email(form.getEmail())
//                .nickname(form.getNickname())
                .password(passwordEncoder.encode(form.getPassword()))
                .authorities(authorities)// USER 권한 부여
                .build();

        Member savedMember = memberRepository.save(member);
        return MemberDTO.from(member);
    }

    @Transactional
    public SignInResult signIn(String email, String password) throws RuntimeException {
        log.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
        Member member = memberRepository.getByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with email: " + email));
        log.info("[getSignInResult] Id : {}", email);

        log.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException();
        }
        log.info("[getSignInResult] 패스워드 일치");

        log.info("[getSignInResult] SignInResultDto 객체 생성");

        SignInResult signInResultDto = SignInResult.builder()
                .token(jwtTokenProvider.createToken(member.getEmail()))
                .build();

        log.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(SignUpResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    // 결과 모델에 api 요청 실패 데이터를 세팅해주는 메소드
    private void setFailResult(SignUpResult result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
