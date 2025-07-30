package br.com.teles.screenmatch_spring.Main;

import br.com.teles.screenmatch_spring.model.EpisodeData;
import br.com.teles.screenmatch_spring.model.SeasonData;
import br.com.teles.screenmatch_spring.model.SeriesData;
import br.com.teles.screenmatch_spring.service.ConvertsData;
import br.com.teles.screenmatch_spring.service.FetchApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    }
}
