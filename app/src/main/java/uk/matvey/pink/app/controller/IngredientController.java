package uk.matvey.pink.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.matvey.pink.app.ingredient.Ingredient;
import uk.matvey.pink.app.ingredient.IngredientRepository;

import java.util.Collection;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/ingredients")
    public Collection<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredient(@PathVariable UUID id) {
        return ingredientRepository.getReferenceById(id);
    }

    @PostMapping("/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createIngredient(@RequestBody CreateIngredientRequest request) {
        var id = randomUUID();
        ingredientRepository.save(new Ingredient(id, request.name));
        return id;
    }

    public static class CreateIngredientRequest {

        public String name;
    }
}
