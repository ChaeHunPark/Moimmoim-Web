package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    FreeBoard save(FreeBoard freeBoard);

    List<FreeBoard> findAll();
}
