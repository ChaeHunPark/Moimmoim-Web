package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.Board;
import com.example.MoimMoim.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

}

