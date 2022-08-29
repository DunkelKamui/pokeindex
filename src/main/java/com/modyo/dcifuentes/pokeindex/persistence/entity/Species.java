package com.modyo.dcifuentes.pokeindex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Species {
    Long id;
    String name;
    Long order;
    @JsonProperty("gender_rate")
    Long genderRate;
    @JsonProperty("capture_rate")
    Long captureRate;
    @JsonProperty("base_happiness")
    Long baseHappiness;
    @JsonProperty("is_baby")
    Boolean isBaby;
    @JsonProperty("is_legendary")
    Boolean isLegendary;
    @JsonProperty("is_mythical")
    Boolean isMythical;
    @JsonProperty("hatch_counter")
    Long hatchCounter;
    @JsonProperty("has_gender_differences")
    Boolean hasGenderDifferences;
    @JsonProperty("forms_switchable")
    Boolean formsSwitchable;
    @JsonProperty("growth_rate")
    Resource growthRate;
    @JsonProperty("pokedex_numbers")
    List<PokemonSpeciesDexEntry> pokedexNumbers;
    @JsonProperty("egg_groups")
    List<Resource> eggGroups;
    Resource color;
    Resource shape;
    @JsonProperty("evolves_from_species")
    Resource evolvesFromSpecies;
    @JsonProperty("evolution_chain")
    Resource evolutionChain;
    Resource habitat;
    Resource generation;
    @JsonProperty("pal_park_encounters")
    List<PalParkEncounterArea> palParkEncounterArea;
    @JsonProperty("flavor_text_entries")
    List<FlavorText> flavorTextEntries;
    List<PokemonSpeciesVariety> varieties;
}
