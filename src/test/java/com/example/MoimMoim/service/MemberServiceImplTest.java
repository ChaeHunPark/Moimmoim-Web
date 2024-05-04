package com.example.MoimMoim.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.MoimMoim.domain.Authority;
import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.dto.MemberDto;
import com.example.MoimMoim.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;

public class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // 가상의 회원 정보 생성 메서드
    private MemberDto createMemberDto() {
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("testUser");
        memberDto.setPassword("testPassword");
        memberDto.setEmail("test@example.com");
        memberDto.setPhone("123456789");
        memberDto.setName("Test User");
        memberDto.setBirthdate("2000-01-01");
        memberDto.setGender("Male");
        memberDto.setNickname("testNickname");
        memberDto.setQuestion("question");
        memberDto.setAnswer("answer");
        return memberDto;
    }

    // 가상의 회원을 저장할 때 리턴할 회원 생성
    private Member createSavedMember() {
        return Member.builder()
                .memberId(1L)
                .username("testUser")
                .password("encodedPassword")
                .email("test@example.com")
                .phone("123456789")
                .name("Test User")
                .birthdate("2000-01-01")
                .gender("Male")
                .nickname("testNickname")
                .question("question")
                .answer("answer")
                .authorities(Collections.singleton(Authority.builder().authority_name("ROLE_USER").build()))
                .activated(true)
                .build();
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void testSignUp() {
        // 가짜 사용자 정보 생성
        MemberDto memberDto = createMemberDto();

        // 가짜 사용자를 저장할 때 리턴할 가짜 사용자 생성
        Member savedMember = createSavedMember();

        // memberRepository.save()가 호출될 때 가짜 사용자를 리턴하도록 설정
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        // passwordEncoder.encode()가 호출될 때 인코딩된 비밀번호를 리턴하도록 설정
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // 회원가입 메서드 호출
        MemberDto result = memberService.signUp(memberDto);

        // 회원가입 결과 검증
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encodedPassword", savedMember.getPassword()); // 비밀번호가 올바르게 인코딩되었는지 확인
        assertTrue(savedMember.getAuthorities().stream().anyMatch(auth -> auth.getAuthority_name().equals("ROLE_USER"))); // 사용자가 "ROLE_USER" 권한을 가지고 있는지 확인
        assertTrue(savedMember.isActivated()); // 사용자가 활성화되어 있는지 확인
    }

    @Test
    @DisplayName("username 중복 검사")
    public void testExistsByUsername() {
        // MemberRepository.existsByUsername(username) 메서드가 true를 반환하도록 설정
        when(memberRepository.existsByUsername("testUser")).thenReturn(true);

        // existsByUsername 메서드 호출
        boolean result = memberService.isUsernameAlreadyInUse("testUser");

        // 결과 확인
        assertTrue(result); // 예상대로 true가 반환되는지 확인
    }

    @Test
    @DisplayName("Nickname 중복검사")
    public void testExistsByNickname() {
        // MemberRepository.existsByNickname(nickname) 메서드가 true를 반환하도록 설정
        when(memberRepository.existsByNickname("testNickname")).thenReturn(true);

        // existsByNickname 메서드 호출
        boolean result = memberService.isNicknameAlreadyInUse("testNickname");

        // 결과 확인
        assertTrue(result); // 예상대로 true가 반환되는지 확인
    }


}