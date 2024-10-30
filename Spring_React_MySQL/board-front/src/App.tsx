import React from 'react';
import './App.css';
import BoardItem from './components/BoardItem';
import CommentItem from 'components/CommentItem';
import FavoriteItem from 'components/FavoritItem';
import Top3Item from 'components/BoardItem/Top3Item';
import { commentListMock, favoriteListMock, latestBoardListMock, top3BoardListMock } from 'mocks';

function App() {
    return (
        <>
            {/* {latestBoardListMock.map(boardListItem => < BoardItem boardListItem={boardListItem} />)} */}
            {/* <div style={{ display: 'flex', justifyContent: 'center', gap: '24px' }}>
                {top3BoardListMock.map(top3ListItem => < Top3Item top3ListItem={top3ListItem} /> )}
            </div> */}
            {/* <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', gap: '30px'  }}>
                {commentListMock.map(commpentListItem => < CommentItem commentListItem={commpentListItem} /> )}
            </div> */}
            <div style={{ display: 'flex', columnGap: '30px', rowGap: '20px'  }}>
                {favoriteListMock.map(favoriteListItem => < FavoriteItem favoriteListItem={favoriteListItem} /> )}
            </div>
        </>
    );
}

export default App;
