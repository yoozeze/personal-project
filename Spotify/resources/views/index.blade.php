@extends('inc.layout')

@section('main')
    <main>
        <div class="login_box">
            <a href="https://www.spotify.com/kr-ko/signup?forward_url=https%3A%2F%2Fopen.spotify.com%2F">가입하기</a>
            <a href="https://accounts.spotify.com/ko/login?continue=https%3A%2F%2Fopen.spotify.com%2F" class="login">로그인</a>
        </div>
        <div class="test"></div>
    @include('inc.footer')
    </main>
@endsection