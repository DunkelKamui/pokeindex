package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlavorText {
    @JsonProperty("flavor_text")
    String flavorTextEntry;
    Resource language;
    Resource version;
}
