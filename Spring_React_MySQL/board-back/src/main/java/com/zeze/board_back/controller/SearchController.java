package com.zeze.board_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeze.board_back.dto.response.search.GetPopularListResponseDto;
import com.zeze.board_back.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/v1/search")
@RequiredArgsConstructor

public class SearchController {

    private final SearchService searchService;

    // 인기 검색어 리스트
    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }
    

}
