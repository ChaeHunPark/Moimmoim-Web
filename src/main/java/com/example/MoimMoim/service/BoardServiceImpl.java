package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.FreeBoard;
import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.domain.MoimBoard;
import com.example.MoimMoim.dto.FreeBoardDto;
import com.example.MoimMoim.dto.MoimBoardDto;
import com.example.MoimMoim.repository.FreeBoardRepository;
import com.example.MoimMoim.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final MemberRepository memberRepository;
    private final FreeBoardRepository freeBoardRepository;



    @Autowired
    public BoardServiceImpl(MemberRepository memberRepository, FreeBoardRepository freeBoardRepository) {
        this.memberRepository = memberRepository;
        this.freeBoardRepository = freeBoardRepository;
    }

    @Transactional
    public FreeBoard FreeBoardSave(FreeBoardDto freeBoardDto) {
        // 멤버에서 유저아이디를 찾는다.
        Member member = memberRepository.findById(freeBoardDto.getId());

        FreeBoard newFreeBoard = new FreeBoard();
        newFreeBoard.setMember(member);
        newFreeBoard.setTitle(freeBoardDto.getTitle());
        newFreeBoard.setCategory(freeBoardDto.getCategory());
        newFreeBoard.setContent(freeBoardDto.getContent());

        return freeBoardRepository.save(newFreeBoard);
    }

    @Override
    public MoimBoard MoimBoardSave(MoimBoardDto moimBoardDto) {
        Member member = memberRepository.findById(moimBoardDto.getId());
        return null;
    }

    @Override
    public List<FreeBoard> getAllFreeBoards() {
        return freeBoardRepository.findAll();
    }
}
