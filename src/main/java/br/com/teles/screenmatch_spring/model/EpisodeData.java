package br.com.teles.screenmatch_spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer num,
                          @JsonAlias("imdbRating") String rate,
                          @JsonAlias("Released") String releaseDate) {
}
