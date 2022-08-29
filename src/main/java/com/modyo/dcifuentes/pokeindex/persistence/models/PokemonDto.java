package com.modyo.dcifuentes.pokeindex.persistence.models;

import lombok.Data;

import java.util.List;

@Data
public class PokemonDto {
    Long id;
    String name;
    List<String> types;
    Long weight;
    List<String> abilities;
    String urlDefault;
}
