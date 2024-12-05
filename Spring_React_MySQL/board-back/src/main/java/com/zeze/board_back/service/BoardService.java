package com.zeze.board_back.service;

import org.springframework.http.ResponseEntity;

import com.zeze.board_back.dto.request.board.PostBoardRequsetDto;
import com.zeze.board_back.dto.request.board.PostCommentRequestDto;
import com.zeze.board_back.dto.response.board.DeleteBoardResponseDto;
import com.zeze.board_back.dto.response.board.GetBoardRespnoseDto;
import com.zeze.board_back.dto.response.board.GetCommentListResponseDto;
import com.zeze.board_back.dto.response.board.GetFavoriteListRespnseDto;
import com.zeze.board_back.dto.response.board.IncreaseViewCountResponseDto;
import com.zeze.board_back.dto.response.board.PostBoardResponseDto;
import com.zeze.board_back.dto.response.board.PostCommentResponseDto;
import com.zeze.board_back.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    
    ResponseEntity<? super GetBoardRespnoseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListRespnseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequsetDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);
    
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
    
}
