package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member save(Member member);
    Member findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByUsername(String username);

}
