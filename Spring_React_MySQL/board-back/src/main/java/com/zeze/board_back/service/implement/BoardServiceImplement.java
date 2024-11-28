package com.zeze.board_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zeze.board_back.dto.request.board.PostBoardRequsetDto;
import com.zeze.board_back.dto.response.ResponseDto;
import com.zeze.board_back.dto.response.board.GetBoardRespnoseDto;
import com.zeze.board_back.dto.response.board.PostBoardResponseDto;
import com.zeze.board_back.dto.response.board.PutFavoriteResponseDto;
import com.zeze.board_back.entity.BoardEntity;
import com.zeze.board_back.entity.FavoriteEntity;
import com.zeze.board_back.entity.ImageEntity;
import com.zeze.board_back.repository.BoardRepository;
import com.zeze.board_back.repository.FavoriteRepository;
import com.zeze.board_back.repository.ImageRepository;
import com.zeze.board_back.repository.UserRepository;
import com.zeze.board_back.repository.resultSet.GetBoardResultSet;
import com.zeze.board_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class BoardServiceImplement implements BoardService{

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    
    // 게시물 조회
    @Override
    public ResponseEntity<? super GetBoardRespnoseDto> getBoard(Integer boardNumber) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {
            
            resultSet = boardRepository.getBoard(boardNumber);
            if (resultSet == null) return GetBoardRespnoseDto.notExistBoard();

            imageEntities = imageRepository.findByBoardNumber(boardNumber);

            // 조회수 증가
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardRespnoseDto.success(resultSet, imageEntities);
    }

    // 게시물 작성
    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequsetDto dto, String email) {
        
        try {

            boolean existedEmail = userRepository.existsByEmail(email);
            if (!existedEmail) return PostBoardResponseDto.notExistUser();
            
            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();
            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image: boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();

    }

    // 좋아요
    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {
        
        try {

            // 유저 존재 확인
            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PutFavoriteResponseDto.noExistUser();

            // 게시물 존재 확인
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return PutFavoriteResponseDto.noExistBoard();

            // 좋아요 기능
            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber, email);
            if (favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email, boardNumber);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            } else {
                // 좋아요 취소
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();

    }

}
