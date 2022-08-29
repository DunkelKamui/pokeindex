package com.modyo.dcifuentes.pokeindex.persistence.models;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class PokemonDetailDto {
    Long id;
    String name;
    List<String> types;
    Long weight;
    Long height;
    List<String> abilities;
    String descripcion;
    Map<String,Integer> evoluciones;
    // Sprites:
    String urlBackDefault;
    String urlBackFemale;
    String urlBackShiny;
    String urlBackShinyFemale;
    String urlFrontDefault;
    String urlFrontFemale;
    String urlFrontShiny;
    String urlFrontShinyFemale;
    EvolucionDto cadenaEvolutiva;
}
