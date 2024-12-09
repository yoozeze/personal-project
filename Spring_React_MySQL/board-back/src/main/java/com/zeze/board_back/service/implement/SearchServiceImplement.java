package com.zeze.board_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zeze.board_back.dto.response.ResponseDto;
import com.zeze.board_back.dto.response.search.GetPopularListResponseDto;
import com.zeze.board_back.repository.SearchLogRepository;
import com.zeze.board_back.repository.resultSet.GetPopularListResultSet;
import com.zeze.board_back.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SearchServiceImplement implements SearchService{

    private final SearchLogRepository searchLogRepository;

    // 인기 검색어 리스트
    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getPopularList();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }

}
