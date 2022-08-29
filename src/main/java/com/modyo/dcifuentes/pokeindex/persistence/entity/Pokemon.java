package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    Long id;
    String name;
    Long weight;
    Long height;
    List<PokemonAbility> abilities;
    List<PokemonType> types;
    Sprites sprites;
    Resource species;
}
