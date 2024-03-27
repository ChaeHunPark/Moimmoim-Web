package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.MoimBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimBoardRepositroy extends JpaRepository<MoimBoard, Long> {
    MoimBoard save(MoimBoard moimBoard);
}
