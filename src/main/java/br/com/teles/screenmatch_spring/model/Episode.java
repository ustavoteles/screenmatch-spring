package br.com.teles.screenmatch_spring.model;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer numEpisode;
    private Double rate;
    private LocalDate releaseDate;

    public Episode(Integer numSeason, EpisodeData episodeData) {
        this.season = numSeason;
        this.title = episodeData.title();
        this.numEpisode = episodeData.num();
        try {
            this.rate = Double.valueOf(episodeData.rate());
        } catch (NumberFormatException ex) {
            this.rate = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(episodeData.releaseDate());
        } catch (DateTimeParseException ex) {
            this.releaseDate = null;
        }
    }


    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return numEpisode;
    }

    public void setNum(Integer numEpisode) {
        this.numEpisode = numEpisode;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", numEpisode=" + numEpisode +
                ", rate=" + rate +
                ", releaseDate=" + releaseDate;
    }
}
