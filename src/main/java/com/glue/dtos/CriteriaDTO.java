package com.glue.dtos;

import lombok.Data;

@Data
public class CriteriaDTO {

    private String nameCriteria;
    private int weight;

    public CriteriaDTO(String nameCriteria, int weight) {
        this.nameCriteria = nameCriteria;
        this.weight = weight;
    }
}
