package com.modyo.dcifuentes.pokeindex.persistence.models;

import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    int count;
    int size;
    List<PokemonDto> pokemonList;
}
