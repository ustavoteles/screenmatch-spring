package br.com.teles.screenmatch_spring.service;

// Traz as ferramentas que a gente vai usar pra fazer requisição HTTP (buscar coisa na internet, tipo API)
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Essa classe aqui é o corre: ela vai buscar os dados na API e devolver pra você.
public class FetchApi {

    // Metodo que faz o trampo: recebe o endereço da API e te devolve a resposta em formato de texto (JSON)
    public String getData(String address) {
        // Cria o "entregador" HTTP. Ele vai lá bater na porta da API e pedir os dados.
        HttpClient client = HttpClient.newHttpClient();

        // Monta a requisição com o endereço da API. Tipo "quero isso aqui, por favor".
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();

        // Aqui a gente prepara uma caixinha pra guardar a resposta da API.
        HttpResponse<String> response = null;

        // Manda a requisição e espera a resposta (esse é o momento tenso do corre)
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Se der ruim de conexão ou algo do tipo, lança o erro (deu ruim no rolê)
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            // Se a requisição for interrompida no caminho, também dá ruim
            throw new RuntimeException(e);
        }

        // Pega o conteúdo da resposta (geralmente vem em JSON bonitinho)
        String json = response.body();

        // E aí sim, devolve esse conteúdo pra quem chamou o método
        return json;
    }
}
