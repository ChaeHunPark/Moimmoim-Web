package com.example.MoimMoim.controller;
import com.example.MoimMoim.domain.FreeBoard;
import com.example.MoimMoim.dto.FreeBoardDto;
import com.example.MoimMoim.dto.MoimBoardDto;
import com.example.MoimMoim.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping("/moim-board")
    public ResponseEntity<String> saveMoimBoardData(@RequestBody MoimBoardDto moimBoardDto) {
        // 받은 데이터 처리
        System.out.println("Received data from moim-board endpoint: " + moimBoardDto.toString());

        // 처리 결과 반환
        return ResponseEntity.ok("Data received successfully from moim-board endpoint!");
    }

    @PostMapping("/free-board")
    public ResponseEntity<String> saveFreeBoardData(@RequestBody FreeBoardDto freeBoardDto) {
        // 받은 데이터 처리

        System.out.println(freeBoardDto.toString());

        boardService.FreeBoardSave(freeBoardDto);

        // 처리 결과 반환
        return ResponseEntity.ok(freeBoardDto.toString());


    }
    @GetMapping("/get-all-free-boards")
    public List<FreeBoard> getFreeBoards() {
        return boardService.getAllFreeBoards();
    }

}
