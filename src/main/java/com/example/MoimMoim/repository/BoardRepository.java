package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    /*
    * 조회
    * */
    @Query("SELECT p.boardId, p.title, p.dateTime, m.nickname " +
            "FROM Board p " +
            "JOIN p.member m " +
            "ORDER BY p.boardId DESC")
    List<Object[]> findAllBoardsWithMemberNickname(); // 게시글 리스트 조회


}
