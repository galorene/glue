package com.glue.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable, Comparable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sales_units")
    private int salesUnits;
    @Column(name = "stocks")
    private String stocks;

    private transient int valor;

    public Product() {
    }

    public Product(int id, String name, int salesUnits, String stocks) {
        this.id = id;
        this.name = name;
        this.salesUnits = salesUnits;
        this.stocks = stocks;
    }


    @Override
    public int compareTo(Object o) {
        return compareTo(o);
    }
}
