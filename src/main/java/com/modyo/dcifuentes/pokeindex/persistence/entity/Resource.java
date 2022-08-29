package com.modyo.dcifuentes.pokeindex.persistence.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Resource implements Serializable {
    private String name;
    private String url;
}
