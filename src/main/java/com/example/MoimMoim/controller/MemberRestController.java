package com.example.MoimMoim.controller;
import com.example.MoimMoim.dto.MemberRequestDto;
import com.example.MoimMoim.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MemberRestController {

    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public String Registration(@RequestBody MemberRequestDto memberRequestDto){ //dto로 이용한 request 매핑
        System.out.println(memberRequestDto.getId());
        memberService.signUp(memberRequestDto);
        return "회원가입이 완료 되었습니다.";
    }


    @PostMapping("/check-id")
    public boolean CheckId(@RequestBody MemberRequestDto memberRequestDto){
        if(memberService.checkIfMemberExists(memberRequestDto.getId())){
            return true;
        }
        return false;
    };

    @PostMapping("/check-nickname")
    public boolean CheckNickname(@RequestBody MemberRequestDto memberRequestDto){
        if(memberService.checkIfNicknameExists(memberRequestDto.getNickname())){
            return true;
        }
        return false;
    }

}
