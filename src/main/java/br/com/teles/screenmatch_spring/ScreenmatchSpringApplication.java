package br.com.teles.screenmatch_spring;

import br.com.teles.screenmatch_spring.model.SeriesData;
import br.com.teles.screenmatch_spring.service.ConvertsData;
import br.com.teles.screenmatch_spring.service.FetchApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchSpringApplication implements CommandLineRunner {
//permite que crie dentro do metodo principal algumas chamadas

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchSpringApplication.class, args);
    }

    //o run virou como se fosse um método main
    @Override
    public void run(String... args) throws Exception {
        var fetchApi = new FetchApi();
        var json = fetchApi.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=c5a15247");
        //      System.out.println(json);
//        json = fetchApi.getData("https://coffee.alexflipnote.dev/random.json");
//        System.out.println(json);
        ConvertsData conversor = new ConvertsData();
        SeriesData data = conversor.getData(json, SeriesData.class);
        System.out.println(data);
    }
}
