package uk.matvey.pink.app.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.UUID;

@JsonIgnoreProperties
public class Ingredient {

    public UUID id;
    public String name;
    public Instant createdAt;
    public Instant updatedAt;

    public Ingredient(UUID id, String name, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Ingredient() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
