package com.modyo.dcifuentes.pokeindex.persistence.models;

import lombok.Data;

import java.util.List;

@Data
public class EvolucionDto {
    String name;
    Integer nivel;
    List<EvolucionDto> sigNivel;
}
