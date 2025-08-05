// Pacote onde essa classe mora no projeto
package br.com.teles.screenmatch_spring.model;

// Importa as anotações do Jackson pra ler os campos do JSON certinho

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

// Esse record aqui é só um "pacote de dados", imutável, usado pra receber os dados da série vindos da API
public record SeriesData(

        // @JsonAlias é tipo um apelido: diz que no JSON o nome do campo é "Title", mas aqui no código vai ser "title"
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalSeasons,
        @JsonAlias("imdbRating") String rate,
        @JsonAlias("Genre") String genre,
        @JsonAlias("Actors") String actors,
        @JsonAlias("Poster") String poster,
        @JsonAlias("synopsis") String synopsis

        // Se quiser pegar o número de votos do IMDB, descomenta essa linha aqui:
        // @JsonProperty funciona parecido com @JsonAlias, mas é mais direto: ele diz exatamente qual nome
        // do campo no JSON deve ser mapeado, mesmo que o nome da variável seja diferente
        // Ex: se no JSON vier "imdbVotes", isso aqui vai jogar o valor na variável "votes"
        //@JsonProperty("imdbVotes") String votes

) {
}
