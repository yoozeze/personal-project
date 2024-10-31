import { CommentListItem } from "types/interface";
import profileImage from 'assets/image/01_모코코콘1_20_엣큥.png';

const commentListMock: CommentListItem[] = [
    {
        "nickname": "안녕하세요나는모코코",
        "profileImage": profileImage,
        "writeDatetime": "3분전",
        "content": "오늘 점심을 뭐먹을 지 너무 고민이 되는 데 뭐 먹을까?"
    },
    {
        "nickname": "안녕하세요나는모코코",
        "profileImage": null,
        "writeDatetime": "5분전",
        "content": "오늘 점심을 뭐먹을 지 너무 고민이 되는 데 뭐 먹을까?"
    },
    {
        "nickname": "안녕하세요나는모코코",
        "profileImage": null,
        "writeDatetime": "1일전",
        "content": "오늘 점심을 뭐먹을 지 너무 고민이 되는 데 뭐 먹을까?"
    },
]

export default commentListMock;