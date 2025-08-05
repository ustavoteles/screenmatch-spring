package br.com.teles.screenmatch_spring.model;

public enum Category {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private String categoryOmdb;

    Category (String categoryOmdb){
        this.categoryOmdb = categoryOmdb;
    }

    //interpreta dinamicamente e transforma na categoria, percorre a lista de constantes
    //se for igual o valor, ele atribui a categoria
    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
    }
