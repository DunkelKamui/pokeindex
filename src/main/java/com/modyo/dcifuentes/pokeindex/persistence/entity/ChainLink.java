package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainLink {
    @JsonProperty("is_baby")
    boolean isBaby;
    Resource species;
    @JsonProperty("evolves_to")
    List<ChainLink> evolvesTo;
}
