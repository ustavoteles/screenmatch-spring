package br.com.teles.screenmatch_spring;

import br.com.teles.screenmatch_spring.Main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchSpringApplication implements CommandLineRunner {
//permite que crie dentro do metodo principal algumas chamadas

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchSpringApplication.class, args);
    }

    //o run virou como se fosse um metodo main
    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.showMenu();

        // var fetchApi = new FetchApi();
        // json = fetchApi.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=c5a15247");
        //      System.out.println(json);
//        json = fetchApi.getData("https://coffee.alexflipnote.dev/random.json");
//        System.out.println(json);
//        ConvertsData conversor = new ConvertsData();
//        SeriesData data = conversor.getData(json, SeriesData.class);
//        System.out.println(data);
//        json = fetchApi.getData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=c5a15247");
//        EpisodeData episodeData = conversor.getData(json, EpisodeData.class);
//        System.out.println(episodeData);

//        List<SeasonData> seasons = new ArrayList<>();
//
//        for (int i = 1; i <= data.totalSeasons(); i++) {
//            json = fetchApi.getData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=c5a15247");
//            SeasonData seasonData = conversor.getData(json, SeasonData.class);
//            seasons.add(seasonData);
//        }
//        seasons.forEach(System.out::println);
    }
}
