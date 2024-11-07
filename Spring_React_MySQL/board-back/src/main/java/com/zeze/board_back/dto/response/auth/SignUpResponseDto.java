package com.zeze.board_back.dto.response.auth;

import com.zeze.board_back.dto.response.ResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zeze.board_back.common.ResponseCode;
import com.zeze.board_back.common.ResponseMessage;

import lombok.Getter;

@Getter
public class SignUpResponseDto extends ResponseDto {
    
    private SignUpResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    // 회원가입 성공
    public static ResponseEntity<SignUpResponseDto> success() {
        SignUpResponseDto result =  new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 중복 이메일
    public static ResponseEntity<ResponseDto> duplicateEmail() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 중복 닉네임
    public static ResponseEntity<ResponseDto> duplicateNickname() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 중복 전화번호
    public static ResponseEntity<ResponseDto> duplicateTelNumber() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_TEL_NUMBER, ResponseMessage.DUPLICATE_TEL_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
