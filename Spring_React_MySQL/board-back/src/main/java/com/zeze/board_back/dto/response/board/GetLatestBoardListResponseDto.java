package com.zeze.board_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zeze.board_back.common.ResponseCode;
import com.zeze.board_back.common.ResponseMessage;
import com.zeze.board_back.dto.object.BoardListItem;
import com.zeze.board_back.dto.response.ResponseDto;
import com.zeze.board_back.entity.BoardListViewEntity;

import lombok.Getter;

@Getter

public class GetLatestBoardListResponseDto extends ResponseDto{
    
    private List<BoardListItem> latestList;

    private GetLatestBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = BoardListItem.getList(boardListViewEntities);
    } 

    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
