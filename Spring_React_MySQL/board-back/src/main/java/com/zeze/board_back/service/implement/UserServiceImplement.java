package com.zeze.board_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zeze.board_back.dto.request.user.PatchNicknameRequestDto;
import com.zeze.board_back.dto.request.user.PatchProfileImageRequestDto;
import com.zeze.board_back.dto.response.ResponseDto;
import com.zeze.board_back.dto.response.user.GetSignInUserResponseDto;
import com.zeze.board_back.dto.response.user.GetUserResponseDto;
import com.zeze.board_back.dto.response.user.PatchNicknameResponseDto;
import com.zeze.board_back.dto.response.user.PatchProfileImageResponseDto;
import com.zeze.board_back.entity.UserEntity;
import com.zeze.board_back.repository.UserRepository;
import com.zeze.board_back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    
    private final UserRepository userRepository;

    // 특정 유저 확인
    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity = null;

        try {

            // 유저 존재 확인
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.noExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }


    // 로그인 유저 확인
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try {

            // 유저 존재 확인
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.noExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);

    }

    // 유저 닉네임 수정
    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname (PatchNicknameRequestDto dto, String email) {

        try {

            // 유저 존재 확인
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchNicknameResponseDto.noExistUser();

            // 닉네임 중복 확인
            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByEmail(nickname);
            if (existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchNicknameResponseDto.success();
    }

    // 유저 프로필 이미지 수정
    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage (PatchProfileImageRequestDto dto, String email) {

        try {

            // 유저 존재 확인
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchNicknameResponseDto.noExistUser();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);
            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchProfileImageResponseDto.success();
    }
    
}
