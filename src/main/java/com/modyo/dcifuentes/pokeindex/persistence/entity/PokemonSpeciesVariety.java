package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokemonSpeciesVariety {
    @JsonProperty("is_default")
    Boolean isDefault;
    Resource pokemon;
}
