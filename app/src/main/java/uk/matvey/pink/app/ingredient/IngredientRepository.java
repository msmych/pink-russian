package uk.matvey.pink.app.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

}
