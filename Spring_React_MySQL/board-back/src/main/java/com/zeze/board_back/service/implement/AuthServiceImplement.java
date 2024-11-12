package com.zeze.board_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeze.board_back.dto.request.auth.SignInRequestDto;
import com.zeze.board_back.dto.request.auth.SignUpRequestDto;
import com.zeze.board_back.dto.response.ResponseDto;
import com.zeze.board_back.dto.response.auth.SignInResponseDto;
import com.zeze.board_back.dto.response.auth.SignUpResponseDto;
import com.zeze.board_back.entity.UserEntity;
import com.zeze.board_back.provider.JwtProvider;
import com.zeze.board_back.repository.UserRepository;
import com.zeze.board_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
 
        try {
            
            String email = dto.getEmail();
            boolean existsByEmail = userRepository.existsByEmail(email);
            if (existsByEmail) return SignUpResponseDto.duplicateEmail();

            String nickName = dto.getNickname();
            boolean existsByNickname = userRepository.existsByNickname(nickName);
            if (existsByNickname) return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existsByTelNumber = userRepository.existsByTelNumber(telNumber);
            if(existsByTelNumber) return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    // 로그인
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        String token = null;

        try {

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(email);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);

    }
    
}
