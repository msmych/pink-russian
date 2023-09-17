package uk.matvey.pink.app.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ingredient {

    @Id
    public UUID id;
    public String name;

    public Ingredient(UUID id, String name) {
        this.id = id;
        this.name = name;
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
