package com.ogu.soonnyang.domain.member.service;

import com.ogu.soonnyang.auth.jwt.JwtTokenProvider;
import com.ogu.soonnyang.config.security.common.CommonResponse;
import com.ogu.soonnyang.domain.member.dto.MemberDTO;
import com.ogu.soonnyang.domain.member.dto.request.SignUpRequest;
import com.ogu.soonnyang.domain.member.dto.request.UpdateMemberRequest;
import com.ogu.soonnyang.domain.member.dto.response.SignInResult;
import com.ogu.soonnyang.domain.member.dto.response.SignUpResult;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.member.exception.MemberNotFoundException;
import com.ogu.soonnyang.domain.member.repository.MemberRepository;
import com.ogu.soonnyang.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignService {

    private final Logger LOGGER = LoggerFactory.getLogger(SignService.class);

    private final MemberRepository memberRepository;
//    private final FeedRepository feedRepository;
    private final PostRepository postRepository;
//    private final DiseaseTimelineRepository diseaseTimelineRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    //이메일 중복체크
    public String checkDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) { //이메일이 있는지?
            return "Y"; //있음 = 중복된 이메일
        } else {
            return "N"; //없음 = 가입 가능한 이메일
        }
    }

    public void changePasswordByEmail(String email, String newPassword) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with email: " + email));
        member.updatePassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
    }

    public void updateMember(Long memberId, UpdateMemberRequest form) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + memberId));
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        member.updateNickname(form.getNickname());

//        memberRepository.save(member);
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + memberId));
    }

    // 회원 가입
    public MemberDTO signUp(SignUpRequest form) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        Member member = Member.builder()
                .email(form.getEmail())
                .nickname(form.getNickname())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();

        Member savedMember = memberRepository.save(member);
        return MemberDTO.from(member);
    }

    public SignInResult signIn(String email, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler 로 회원 정보 요청");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with email: " + email));
        LOGGER.info("[getSignInResult] Id : {}", email);

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResult signInResult = SignInResult.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(member.getEmail()),
                        member.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResult);

        return signInResult;
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
