package br.com.teles.screenmatch_spring.service;

public interface IConvertsData {
    // Metodo genérico que recebe um JSON (em formato String) e a classe do tipo de dado que será convertido.
    // <T> indica que o metodo pode trabalhar com qualquer tipo de objeto.
    // Por exemplo: pode converter um JSON para um objeto Filme, Série, etc.
    <T> T getData(String json, Class<T> clazz);
}
