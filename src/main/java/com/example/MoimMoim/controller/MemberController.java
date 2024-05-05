package com.example.MoimMoim.controller;
import com.example.MoimMoim.dto.MemberDto;
import com.example.MoimMoim.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 회원가입 수행
    @PostMapping("/register")
    public ResponseEntity<MemberDto> registration(@Valid @RequestBody MemberDto memberDto){ //dto로 이용한 request 매핑
        return ResponseEntity.ok(memberService.signUp(memberDto));
    }

    // 닉네임 중복체크 수행
    // return 값 true면 중복
    @GetMapping("/username-duplicate")
    public ResponseEntity<Boolean> checkUsernameExists(@RequestParam("username") String username) {
        boolean exists = memberService.isUsernameAlreadyInUse(username);
        return ResponseEntity.status(HttpStatus.OK).body(exists);
    }

    // 닉네임 중복체크 수행
    // return 값 true면 중복
    @GetMapping("/nickname-duplicate")
    public ResponseEntity<Boolean> checkNicknameExists(@RequestParam("nickname") String nickname){
        boolean exists = memberService.isNicknameAlreadyInUse(nickname);
        return ResponseEntity.status(HttpStatus.OK).body(exists);
    }



    /**
     * 현재 인증된 사용자의 정보와 해당 사용자의 권한을 조회하는 엔드포인트
     * 사용자 및 권한 정보는 MemberDto로 래핑되어 반환
     * 엔드포인트에 접근하려면 사용자는 'USER' 또는 'ADMIN' 역할이 있어야 함
     *
     * @param request 현재 HTTP 요청 객체
     * @return ResponseEntity<MemberDto> 객체 (사용자 정보 및 권한 정보 포함)
     */
    @GetMapping("/validate-token")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }

    /**
     * 특정 사용자의 정보와 해당 사용자의 권한을 조회하는 엔드포인트.
     * 사용자 및 권한 정보는 MemberDto로 래핑되어 반환됩니다.
     * 엔드포인트에 접근하려면 사용자는 'ADMIN' 역할이 있어야 함
     *
     * @param id 조회할 사용자의 ID
     * @return ResponseEntity<MemberDto> 객체 (사용자 정보 및 권한 정보 포함)
     */
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> getUserInfo(@PathVariable String id) {
        return ResponseEntity.ok(memberService.getUserWithAuthorities(id));
    }
}
