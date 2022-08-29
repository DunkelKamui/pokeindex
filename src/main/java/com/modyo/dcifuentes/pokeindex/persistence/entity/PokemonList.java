package com.modyo.dcifuentes.pokeindex.persistence.entity;

import lombok.Data;

import java.util.List;

@Data
public class PokemonList {
    private int count;
    private String next;
    private String previous;
    private List<Resource> results;
}
