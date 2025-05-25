package com.camp.tmdbapidemo.service;

import com.camp.tmdbapidemo.dto.MovieDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final RestClient tmdbRestClient;

    public MovieDetail getMovie(int movieId, String language) {
        return tmdbRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}")
                        .queryParam("language", language)
                        .build(movieId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RuntimeException("Client error occurred for movie ID " + movieId + ": " + response.getStatusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new RuntimeException("Server error occurred for movie ID " + movieId + ": " + response.getStatusCode());
                })
                .body(MovieDetail.class);
    }
}