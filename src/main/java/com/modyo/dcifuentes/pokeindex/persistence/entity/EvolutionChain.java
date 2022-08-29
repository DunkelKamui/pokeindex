package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EvolutionChain {
    Long id;
    @JsonProperty("baby_trigger_item")
    Resource babyTriggerItem;
    ChainLink chain;
}
