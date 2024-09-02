@extends('inc.layout')

@section('main')
    <main>
        <div class="login_box">
            <a href="https://www.spotify.com/kr-ko/signup?forward_url=https%3A%2F%2Fopen.spotify.com%2F" class="regist">가입하기</a>
            <a href="https://accounts.spotify.com/ko/login?continue=https%3A%2F%2Fopen.spotify.com%2F" class="login">로그인하기</a>
        </div>
        <div class="popular_artist_box">
            <a href="" class="popular_artist_title">인기 아티스트</a>
            <a href="" class="all">모두 표시</a>
        </div>
        <div class="popular_artist_content">
            <div class="popular_artist">
                <img src="/img/newjeans.webp" alt="" class="popular_artist_img">
                <p class="artist_name">NewJeans</p>
                <p class="artist">아티스트</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_artist">
                <img src="/img/aespa.webp" alt="" class="popular_artist_img">
                <p class="artist_name">aespa</p>
                <p class="artist">아티스트</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_artist">
                <img src="/img/iu.jpg" alt="" class="popular_artist_img">
                <p class="artist_name">아이유</p>
                <p class="artist">아티스트</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_artist">
                <img src="/img/illit.webp" alt="" class="popular_artist_img">
                <p class="artist_name">ILLIT</p>
                <p class="artist">아티스트</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_artist">
                <img src="/img/kiss_of_life.webp" alt="" class="popular_artist_img">
                <p class="artist_name">KISS OF LIFE</p>
                <p class="artist">아티스트</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_artist">
                <img src="/img/lesserafim.webp" alt="" class="popular_artist_img">
                <p class="artist_name">LE SSERAFIM</p>
                <p class="artist">아티스트</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
        </div>
        <div class="popular_album_box">
            <a href="" class="popula_album_title">인기 앨범</a>
            <a href="" class="all">모두 표시</a>
        </div>
        <div class="popular_album_content">
            <div class="popular_album">
                <img src="/img/muse.webp" alt="" class="popular_album_img">
                <p class="album_name">MUSE</p>
                <p class="album_artist_name">지민</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_album">
                <img src="/img/golden.webp" alt="" class="popular_album_img">
                <p class="album_name">GOLDEN</p>
                <p class="album_artist_name">정국</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_album">
                <img src="/img/imhero.webp" alt="" class="popular_album_img">
                <p class="album_name">IM HERO</p>
                <p class="album_artist_name">임영웅</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_album">
                <img src="/img/who.jpg" alt="" class="popular_album_img">
                <p class="album_name">Who (Remixes)</p>
                <p class="album_artist_name">지민</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_album">
                <img src="/img/eternal.webp" alt="" class="popular_album_img">
                <p class="album_name">ETERNAL</p>
                <p class="album_artist_name">태민</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
            <div class="popular_album">
                <img src="/img/asterum.webp" alt="" class="popular_album_img">
                <p class="album_name">ASTERUM : 134-1</p>
                <p class="album_artist_name">PLAVE</p>
                <img src="/img/play.png" alt="" class="popular_play_btn">
            </div>
        </div>
    @include('inc.footer')
    </main>
@endsection