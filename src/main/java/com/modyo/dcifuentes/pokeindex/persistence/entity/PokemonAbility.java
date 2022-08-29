package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PokemonAbility implements Serializable {
    Resource ability;
    @JsonProperty("is_hidden")
    boolean isHidden;
    long slot;
}
