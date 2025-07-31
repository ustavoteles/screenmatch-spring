package br.com.teles.screenmatch_spring.Main;

import br.com.teles.screenmatch_spring.model.Episode;
import br.com.teles.screenmatch_spring.model.EpisodeData;
import br.com.teles.screenmatch_spring.model.SeasonData;
import br.com.teles.screenmatch_spring.model.SeriesData;
import br.com.teles.screenmatch_spring.service.ConvertsData;
import br.com.teles.screenmatch_spring.service.FetchApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    private Scanner read = new Scanner(System.in);
    private FetchApi fetchApi = new FetchApi();
    private ConvertsData conversor = new ConvertsData();


    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c5a15247";

    public void showMenu() {
        System.out.println("Digite o nome da série ");
        var seriesName = read.nextLine();
        fetchApi = new FetchApi();
        var json = fetchApi.getData(
                ADDRESS + seriesName.replace(" ", "+") + API_KEY);
        SeriesData data = conversor.getData(json, SeriesData.class);
        System.out.println(data);

        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= data.totalSeasons(); i++) {
            json = fetchApi.getData(ADDRESS + seriesName.replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = conversor.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
        // seasons.forEach(s -> System.out.println(s));
        //acima expressão lambda, nesse caso
        // usado somente quando é um parâmetro


//        for (int i = 0; i < data.totalSeasons(); i++) {
//            List<EpisodeData> seasonEpisodes = seasons.get(i).episodes();
//            for (int j = 0; j < seasonEpisodes.size(); j++) {
//                System.out.println(seasonEpisodes.get(j).title());
//            }
//        }

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
        //outra expressão lambda usando duas variáveis

        List<EpisodeData> episodesData = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());
        //to list seria uma lista imutável, nao da pra acrescentar, nao da pra alterar

        System.out.println("\nTop 5 episódios");
        episodesData.stream()
                .filter(e -> !e.rate().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rate).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(s.num(), e))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios? ");
        var year = read.nextInt();
        read.nextLine();

        LocalDate searchDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodes.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getSeason() +
                                "Episódio: " + e.getTitle() +
                                "Data lançamento: " + e.getReleaseDate().format(dtf)
                ));
    }
}
