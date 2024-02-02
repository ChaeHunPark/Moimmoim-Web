package com.example.MoimMoim.Controller;
import com.example.MoimMoim.dto.MemberRequestDto;
import com.example.MoimMoim.service.MemberService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MemberRestController {


    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public String Registration(@RequestBody MemberRequestDto memberRequestDto){ //dto로 이용한 request 매핑
        memberService.signUp(memberRequestDto);
        return "회원가입이 완료 되었습니다.";
    }
}
