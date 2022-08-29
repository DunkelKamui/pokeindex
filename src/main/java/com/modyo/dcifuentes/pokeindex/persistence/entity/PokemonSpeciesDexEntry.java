package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokemonSpeciesDexEntry {
    @JsonProperty("entry_number")
    Long entryNumber;
    Resource pokedex;
}
