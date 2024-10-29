export default interface BoardListItem {
    boardNumer: number;
    title: string;
    content: string;
    boardTitleImage: string | null;
    favoriteCount: number;
    commentCount: number;
    viewCount: number;
    writeDatetime: string;
    writeNickname: string;
    writeProfileImage: string | null;
}