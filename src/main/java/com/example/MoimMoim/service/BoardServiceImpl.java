package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.BoardComment;
import com.example.MoimMoim.domain.Enum.BoardType;
import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.domain.BoardMoim;
import com.example.MoimMoim.domain.Board;
import com.example.MoimMoim.dto.Board.*;
import com.example.MoimMoim.repository.BoardCommentRepository;
import com.example.MoimMoim.repository.MemberRepository;
import com.example.MoimMoim.repository.BoardMoimRepository;
import com.example.MoimMoim.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardMoimRepository boardMoimRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Autowired
    public BoardServiceImpl(MemberRepository memberRepository, BoardRepository boardRepository,
                            BoardMoimRepository boardMoimRepository, BoardCommentRepository boardCommentRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.boardMoimRepository = boardMoimRepository;
        this.boardCommentRepository = boardCommentRepository;
    }

    /*
    * 모임글쓰기
    * */
    public void createBoardMoim(BoardMoimDto boardMoimDto) {
        Board board = createBoard(boardMoimDto.getBoard());

        Member member = memberRepository.findById(boardMoimDto.getBoard().getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + boardMoimDto.getBoard().getMemberId()));

        BoardMoim boardMoim = BoardMoim.builder()
                .latitude(boardMoimDto.getLatitude())
                .longitude(boardMoimDto.getLongitude())
                .participantLimit(boardMoimDto.getParticipantLimit())
                .ageLimit(boardMoimDto.getAgeLimit())
                .status(boardMoimDto.getStatus())
                .board(board)
                .build();

        boardMoimRepository.save(boardMoim);
    }

    /*
    * 글쓰기
    * BoardMoim 생성할 때 Board 영속화를 위해 save를 리턴
    * */
    public Board createBoard(BoardDto boardDto) {
        Member member = memberRepository.findById(boardDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + boardDto.getMemberId()));

        Board board = Board.builder()
                .member(member)
                .category(boardDto.getCategory())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .boardType(boardDto.getBoardType())
                .build();


        return boardRepository.save(board);
    }

    /*
    * 댓글 작성
    * */

    @Override
    public void boardCommentWriting(BoardCommentDto boardCommentDto) {
        Board board = boardRepository.findById(boardCommentDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("board not found with id: " + boardCommentDto.getBoardId()));
        Member member = memberRepository.findById(boardCommentDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("member not found with id: " + boardCommentDto.getMemberId()));


        BoardComment boardComment = BoardComment.builder()
                .board(board)
                .member(member)
                .content(boardCommentDto.getContent())
                .build();
        boardCommentRepository.save(boardComment);
    }

    /*
    * 게시글 리스트 조회
    * */

    public List<Object[]> getAllBoardsWithMemberNickname() {
        return boardRepository.findAllBoardsWithMemberNickname();
    }



}
