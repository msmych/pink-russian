package uk.matvey.pink.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.matvey.pink.app.ingredient.Ingredient;
import uk.matvey.pink.app.ingredient.IngredientRepo;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
public class IngredientController {

    private final IngredientRepo ingredientRepo;

    public IngredientController(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping("/ingredients")
    public Collection<Ingredient> getIngredients() {
        return ingredientRepo.selectAll();
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredient(@PathVariable UUID id) {
        return ingredientRepo.selectById(id);
    }

    @PostMapping("/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createIngredient(@RequestBody CreateIngredientRequest request) {
        var id = randomUUID();
        final var now = Instant.now();
        ingredientRepo.insert(new Ingredient(id, request.name, now, now));
        return id;
    }

    public static class CreateIngredientRequest {

        public String name;
    }
}
