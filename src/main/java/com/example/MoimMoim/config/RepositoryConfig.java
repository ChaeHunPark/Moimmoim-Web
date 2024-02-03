package com.example.MoimMoim.config;

import com.example.MoimMoim.repository.JpaMemberRepository;
import com.example.MoimMoim.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    private EntityManager em;

    public RepositoryConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
