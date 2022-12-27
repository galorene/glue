package com.glue.dtos;

import lombok.Data;

@Data
public class ProductDTO {

    private int id;
    private String name;
    private int salesUnits;
    private String stocks;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, int salesUnits, String stocks) {
        this.id = id;
        this.name = name;
        this.salesUnits = salesUnits;
        this.stocks = stocks;
    }

}
