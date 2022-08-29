package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprites {
    @JsonProperty("back_default")
    String urlBackDefault;
    @JsonProperty("back_female")
    String urlBackFemale;
    @JsonProperty("back_shiny")
    String urlBackShiny;
    @JsonProperty("back_shiny_female")
    String urlBackShinyFemale;
    @JsonProperty("front_default")
    String urlFrontDefault;
    @JsonProperty("front_female")
    String urlFrontFemale;
    @JsonProperty("front_shiny")
    String urlFrontShiny;
    @JsonProperty("front_shiny_female")
    String urlFrontShinyFemale;
}
