import axios from "axios";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import { SignInResponseDto } from "./response/auth";
import { ResponseDto } from "./response";

const DOMAIN = 'http://localhost:4000';

const API_DOMAIN = `${DOMAIN}/api/v1`;

const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

export const signInRequest = async (requestBody: SignInRequestDto) => {
    const result = await axios.post(SIGN_IN_URL(), requestBody)
        .then(response => {
            const responseBody: SignInResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        })

    return result;
}

export const signUpRequest = async (requestBody: SignUpRequestDto) => {

}
// export const signInRequest = async (requestBody: SignInRequestDto): Promise<SignInResponseDto | ResponseDto | null> => {
//     try {
//         // 성공적으로 응답을 받은 경우
//         const response = await axios.post(SIGN_IN_URL(), requestBody);
//         const responseBody: SignInResponseDto = response.data;
//         return responseBody;
//     } catch (error: any) {
//         // 네트워크 오류 또는 서버 오류 처리
//         if (error.response && error.response.data) {
//             const responseBody: ResponseDto = error.response.data;
//             return responseBody;
//         } else {
//             console.error('Unexpected Error:', error);
//             return null; // 예기치 못한 오류
//         }
//     }
// };