@extends('inc.layout')

@section('main')
    <main>
        <div class="login_box">
            <a href="https://www.spotify.com/kr-ko/signup?forward_url=https%3A%2F%2Fopen.spotify.com%2F">가입하기</a>
            <a href="https://accounts.spotify.com/ko/login?continue=https%3A%2F%2Fopen.spotify.com%2F" class="login">로그인</a>
        </div>
        <div class="popular_artist_box">
            <a href="" class="popular_artist_title">인기 아티스트</a>
            <a href="" class="all">모두 표시</a>
        </div>
        <div class="popular_artist_content">
            <div class="popular_artist">
                <div class="popular_artist_img1"></div>
                <p class="artist_name">NewJeans</p>
                <p class="artist">아티스트</p>
            </div>
            <div class="popular_artist">
                <div class="popular_artist_img2"></div>
                <p class="artist_name">aespa</p>
                <p class="artist">아티스트</p>
            </div>
            <div class="popular_artist">
                <div class="popular_artist_img3"></div>
                <p class="artist_name">아이유</p>
                <p class="artist">아티스트</p>
            </div>
            <div class="popular_artist">
                <div class="popular_artist_img4"></div>
                <p class="artist_name">ILLIT</p>
                <p class="artist">아티스트</p>
            </div>
            <div class="popular_artist">
                <div class="popular_artist_img5"></div>
                <p class="artist_name">KISS OF LIFE</p>
                <p class="artist">아티스트</p>
            </div>
            <div class="popular_artist">
                <div class="popular_artist_img6"></div>
                <p class="artist_name">Taylor Swift</p>
                <p class="artist">아티스트</p>
            </div>
        </div>
    @include('inc.footer')
    </main>
@endsection