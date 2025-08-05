package br.com.teles.screenmatch_spring.Main;

import br.com.teles.screenmatch_spring.model.Episode;
import br.com.teles.screenmatch_spring.model.EpisodeData;
import br.com.teles.screenmatch_spring.model.SeasonData;
import br.com.teles.screenmatch_spring.model.SeriesData;
import br.com.teles.screenmatch_spring.service.ConvertsData;
import br.com.teles.screenmatch_spring.service.FetchApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    private Scanner read = new Scanner(System.in);
    private FetchApi fetchApi = new FetchApi();
    private ConvertsData conversor = new ConvertsData();


    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c5a15247";
    private List<SeriesData> seriesData = new ArrayList<>();

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = read.nextInt();
            read.nextLine();

            switch (option) {
                case 1:
                    searchSeriesWeb();
                    break;

                case 2:
                    searchEpisodePerSeries();
                    break;

                case 3:
                    getSearchedSeries();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    ;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchSeriesWeb() {
        SeriesData data = getDataSeries();
        seriesData.add(data);
        System.out.println(data);

    }


    private SeriesData getDataSeries() {
        System.out.println("Digite o nome da série para busca: ");
        var seriesName = read.nextLine();
        var json = fetchApi.getData(
                ADDRESS + seriesName.replace(" ", "+") + API_KEY);
        SeriesData data = conversor.getData(json, SeriesData.class);
        return data;
    }

    private void searchEpisodePerSeries() {
        SeriesData seriesData = getDataSeries();
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= seriesData.totalSeasons(); i++) {
            var json = fetchApi.getData(ADDRESS + seriesData.title().replace(" ", "+")
                    + "&season=" + i + API_KEY);
            SeasonData seasonData = conversor.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void getSearchedSeries() {
        seriesData.forEach(System.out::println);
    }
}

//
//    fetchApi =new
//
//    FetchApi();
//
//
//        System.out.println(data);
//
//    List<SeasonData> seasons = new ArrayList<>();
//
//        for(
//    int i = 1;
//    i <=data.totalSeasons();i++)
//
//    {
//        json = fetchApi.getData(ADDRESS + seriesName.replace(" ", "+") + "&season=" + i + API_KEY);
//        SeasonData seasonData = conversor.getData(json, SeasonData.class);
//        seasons.add(seasonData);
//    }
//seasons.forEach(System.out::println);
// seasons.forEach(s -> System.out.println(s));
//acima expressão lambda, nesse caso
// usado somente quando é um parâmetro


//        for (int i = 0; i < data.totalSeasons(); i++) {
//            List<EpisodeData> seasonEpisodes = seasons.get(i).episodes();
//            for (int j = 0; j < seasonEpisodes.size(); j++) {
//                System.out.println(seasonEpisodes.get(j).title());
//            }
//        }

//seasons.forEach(s ->s.episodes().

//forEach(e ->System.out.println(e.title())));
//outra expressão lambda usando duas variáveis

//    List<EpisodeData> episodesData = seasons.stream()
//            .flatMap(s -> s.episodes().stream())
//            .collect(Collectors.toList());
//    //to list seria uma lista imutável, nao da pra acrescentar, nao da pra alterar

//        System.out.println("\nTop 5 episódios");
//        episodesData.stream()
//                .
//
//    filter(e ->!e.rate().
//
//    equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
//            .
//
//    sorted(Comparator.comparing(EpisodeData::rate).
//
//    reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//            .

//  limit(5)
//                .peek(e -> System.out.println("limite " + e))
//        .
//
//    map(e ->e.title().
//
//    toUpperCase())
/// /                .peek(e -> System.out.println("Mapeamento " + e))
//            .
//
//    forEach(System.out::println);
//
//    List<Episode> episodes = seasons.stream()
//            .flatMap(s -> s.episodes().stream()
//                    .map(e -> new Episode(s.num(), e))
//            ).collect(Collectors.toList());
//
//        episodes.forEach(System.out::println);
//
//        System.out.println("Digite um trecho do título do episódio: ");
//    var titlePiece = read.nextLine();
//    Optional<Episode> specificEpisode = episodes.stream()
//            .filter(e -> e.getTitle().toUpperCase().contains(titlePiece.toUpperCase()))
//            .findFirst();
//        if(specificEpisode.isPresent())
//
//    {
//        System.out.println("Episódio encontrado!");
//        System.out.println("Temporada: " + specificEpisode.get().getSeason());
//    } else
//
//    {
//        System.out.println("Episódio não encontrado");
//    }
//
//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//    var year = read.nextInt();
//        read.nextLine();
//
//    LocalDate searchDate = LocalDate.of(year, 1, 1);
//
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodes.stream()
//                .
//
//    filter(e ->e.getReleaseDate()!=null&&e.getReleaseDate().
//
//    isAfter(searchDate))
//            .
//
//    forEach(e ->System.out.println(
//            "Temporada: "+e.getSeason()+
//            "Episódio: "+e.getTitle()+
//            "Data lançamento: "+e.getReleaseDate().
//
//    format(dtf)
//                ));
//
//    Map<Integer, Double> ratePerSeason = episodes.stream()
//            .filter(e -> e.getRate() > 0.0)
//            .collect(Collectors.groupingBy(Episode::getSeason,
//                    Collectors.averagingDouble(Episode::getRate)));
//        System.out.println(ratePerSeason);
//
//    DoubleSummaryStatistics est = episodes.stream()
//            .filter(e -> e.getRate() > 0.0)
//            .collect(Collectors.summarizingDouble(Episode::getRate));
//        System.out.println("Média "+est.getAverage());
//        System.out.println("Melhor episódio: "+est.getMax());
//        System.out.println("Pior episódio: "+est.getMin());
//        System.out.println("Quantidade de episódios: "+est.getCount());
//}
//}
