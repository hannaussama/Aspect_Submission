package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lodges")
public class Lodge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private Double rate;

    public Lodge() {}

    public Lodge(String name, String category, Double rate) {
        this.name = name;
        this.category = category;
        this.rate = rate;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }
}
