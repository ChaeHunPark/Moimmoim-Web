package com.example.MoimMoim.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PageController {

    @PostMapping("/{path:[^\\\\.]*}")
    public String PageTracker() {
        return "페이지 방문";
    }
}