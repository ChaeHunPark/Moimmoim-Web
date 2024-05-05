package com.example.MoimMoim.controller;

import com.example.MoimMoim.dto.Board.*;
import com.example.MoimMoim.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board-writing")
    public ResponseEntity<String> createBoard(@RequestBody BoardDto boardDto) {
        try {
            boardService.createBoard(boardDto);
            return new ResponseEntity<>("Board created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/board-moim-writing")
    public ResponseEntity<String> createBoardMoim(@RequestBody BoardMoimDto boardMoimDto) {
        try {
            boardService.createBoardMoim(boardMoimDto);
            return new ResponseEntity<>("Board created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("board-comment-writing")
    public ResponseEntity<String> boardCommnetWriting(@RequestBody BoardCommentDto boardCommentDto){
        try {
            boardService.boardCommentWriting(boardCommentDto);
            return new ResponseEntity<>("BoardComment created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/all-board-list") // boardId, title, datetime, nickname을 함께 조회
    public ResponseEntity<List<Object[]>> getAllBoardsWithMemberNickname() {
        try {
            List<Object[]> boardDetails = boardService.getAllBoardsWithMemberNickname();
            return ResponseEntity.ok(boardDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 에러가 발생하면 500 에러를 반환
        }
    }

}
