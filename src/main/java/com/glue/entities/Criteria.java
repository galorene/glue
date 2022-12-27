package com.glue.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "criteria")
@Data
public class Criteria implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name_criteria")
    private String nameCriteria;
    private transient int weight;

    public Criteria() {
    }

    public Criteria(int id, String nameCriteria) {
        this.id = id;
        this.nameCriteria = nameCriteria;
    }
}
