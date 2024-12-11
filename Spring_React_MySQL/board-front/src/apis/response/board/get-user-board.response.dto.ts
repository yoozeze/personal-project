import { BoardListItem } from "types/interface";
import ResponseDto from "../response.dto";

export default interface GetUserBoardListResponseDton extends ResponseDto {
    userBoardList: BoardListItem[];
}