package br.com.teles.screenmatch_spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Classe que converte dados em JSON para objetos Java
public class ConvertsData implements IConvertsData {

    // Cria um "conversor" de JSON chamado ObjectMapper
    private ObjectMapper mapper = new ObjectMapper();

    //metodo que transforma um texto JSON em um objeto Java do tipo desejado
    @Override
    public <T> T getData(String json, Class<T> clazz) {
        try {
            //Tenta fazer a conversão do JSON para o tipo informado (clazz)
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            //Se der erro na conversão, lança uma exceção para mostrar que algo deu errado
            throw new RuntimeException(e);
        }
    }
}