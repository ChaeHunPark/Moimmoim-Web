package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Board;
import com.example.MoimMoim.dto.Board.*;

import java.util.List;

public interface BoardService {

    /*
    * 생성
    * */
    Board createBoard(BoardDto boardDto); // 게시글 생성
    void createBoardMoim(BoardMoimDto boardMoimDto); //모임 게시글 생성
    void boardCommentWriting(BoardCommentDto boardCommentDto);

    /*
    * 조회
    */
    List<Object[]> getAllBoardsWithMemberNickname(); // 게시글 list 조회

}
