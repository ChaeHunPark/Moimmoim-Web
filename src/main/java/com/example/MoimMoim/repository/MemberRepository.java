package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository{
    Member save(Member member);

    List<String> findMemberById(String id);

}
