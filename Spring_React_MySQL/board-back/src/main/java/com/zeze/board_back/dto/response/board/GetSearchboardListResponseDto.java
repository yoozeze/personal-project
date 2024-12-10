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

public class GetSearchboardListResponseDto extends ResponseDto{

    private List<BoardListItem> searchList;

    private GetSearchboardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetSearchboardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetSearchboardListResponseDto result = new GetSearchboardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
