package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LenskitRecommender;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @GetMapping("/{userId}")
    public List<Long> getRecommendations(@PathVariable long userId) {
        return LenskitRecommender.getRecommendations(userId, 5);
    }
}
