package com.camp.tmdbapidemo.controller;

import com.camp.tmdbapidemo.dto.MovieDetail;
import com.camp.tmdbapidemo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    /**
     {
     "id": 158445,
     "title": "7번방의 선물",
     "overview": "최악의 흉악범들이 모인 교도소 7번방에 이상한 놈이 들어왔다! 그는 바로 6살 지능의 딸바보 용구. 평생 죄만 짓고 살아온 7번방 패밀리들에게 떨어진 미션은 바로 용구 딸 예승이를 외부인 절대 출입금지인 교도소에 반.입.하.는.것! 웃음과 감동 가득한 사상초유의 합동작전이 시작된다!",
     "popularity": 3.0124,
     "release_date": "2013-01-23",
     "vote_average": 7.926,
     "poster_path": "/c9TqJPm4pZCuiEXumTayoNIrBSK.jpg"
     }

     이미지 URL : https://image.tmdb.org/t/p/w500/c9TqJPm4pZCuiEXumTayoNIrBSK.jpg
     */
    @GetMapping("/movies/{movieId}")
    public MovieDetail getMovieDetail(@PathVariable int movieId, @RequestParam(defaultValue = "ko-KR") String language) {
        return movieService.getMovie(movieId, language);
    }
}