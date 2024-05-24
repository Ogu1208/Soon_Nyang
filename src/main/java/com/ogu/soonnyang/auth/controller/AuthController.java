package com.ogu.soonnyang.auth.controller;

import com.ogu.soonnyang.domain.member.dto.MemberDTO;
import com.ogu.soonnyang.domain.member.dto.request.SignInRequest;
import com.ogu.soonnyang.domain.member.dto.request.SignUpRequest;
import com.ogu.soonnyang.domain.member.dto.response.SignInResult;
import com.ogu.soonnyang.domain.member.service.SignService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final SignService signService;

    @Autowired
    public AuthController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/login")
    public SignInResult signIn(
            @Parameter(description = "email", required = true)
            @Valid @RequestBody SignInRequest form) throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", form.getEmail());
        SignInResult signInResult = signService.signIn(form.getEmail(), form.getPassword());

        if (signInResult.getCode() == 0) {
            LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", form.getEmail(),
                    signInResult.getToken());
        }
        return signInResult;
    }

    @PostMapping(value = "/sign-up")
    public MemberDTO signUp(@RequestBody SignUpRequest form) {
        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}",form.getEmail(), form.getNickname());

        MemberDTO memberDTO = signService.signUp(form);

        LOGGER.info("[signUp] 회원가입을 완료했습니다. id : {}", memberDTO.getEmail());
        return memberDTO;
    }

    @GetMapping(value = "/check-email")
    public ResponseEntity<Map<String, String>> checkDuplicateEmail(
            @Parameter(description = "email", required = true) @RequestParam String email){
        Map<String, String> map = new HashMap<>();
        String YorN = signService.checkDuplicateEmail(email); //중복되면Y, 아니면N
        map.put("msg", YorN);
        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/reset-pwd")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody SignInRequest form) {
        LOGGER.info("[resetPW] 패스워드 변경을 시도하고 있습니다. id : {}, pw : ****", form.getEmail());
        signService.changePasswordByEmail(form.getEmail(), form.getPassword());
        Map<String, String> map = new HashMap<>();
        map.put("msg", "비밀번호를 재설정 했습니다.");
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }
}