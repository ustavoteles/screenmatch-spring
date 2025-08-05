package br.com.teles.screenmatch_spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.OptionalDouble;

public class Series {
    private String title;
    private Integer totalSeasons;
    private Double rate;
    private Category genre;
    private String actors;
    private String poster;
    private String synopsis;

    public Series(SeriesData seriesData) {
        this.title = seriesData.title();
        this.totalSeasons = seriesData.totalSeasons();
        this.rate = OptionalDouble.of(Double.valueOf(seriesData.rate())).orElse(0);
        //valor zero se não houver avaliação

        this.genre = Category.fromString(seriesData.genre().split(",")[0].trim());
        this.actors = seriesData.actors();
        this.poster = seriesData.poster();
        this.synopsis = seriesData.synopsis();
    }
}
