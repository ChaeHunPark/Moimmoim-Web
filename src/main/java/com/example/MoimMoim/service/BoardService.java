package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.FreeBoard;
import com.example.MoimMoim.domain.MoimBoard;
import com.example.MoimMoim.dto.FreeBoardDto;
import com.example.MoimMoim.dto.MoimBoardDto;

import java.util.List;

public interface BoardService {
    FreeBoard FreeBoardSave(FreeBoardDto freeBoardDto);
    List<FreeBoard> getAllFreeBoards();

    MoimBoard MoimBoardSave(MoimBoardDto moimBoardDto);
}
