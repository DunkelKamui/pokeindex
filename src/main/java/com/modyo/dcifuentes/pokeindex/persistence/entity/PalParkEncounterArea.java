package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PalParkEncounterArea {
    @JsonProperty("base_score")
    Long baseScore;
    Long rate;
    Resource area;
}
