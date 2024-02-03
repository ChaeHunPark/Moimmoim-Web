package com.example.MoimMoim.repository;

import com.example.MoimMoim.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<String> findMemberById(String id) {
        String jpql = "SELECT m.id FROM Member m Where m.id = :id";
        Query query = em.createQuery(jpql, String.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


}
