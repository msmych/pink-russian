package uk.matvey.pink.app.ingredient;

import org.springframework.stereotype.Repository;
import uk.matvey.pink.freezer.QueryParams;
import uk.matvey.pink.freezer.Repo;
import uk.matvey.pink.freezer.ResultExtractor;

import java.util.List;
import java.util.UUID;

import static uk.matvey.pink.freezer.QueryParam.TextParam.textParam;
import static uk.matvey.pink.freezer.QueryParam.TimestampParam.timestampParam;
import static uk.matvey.pink.freezer.QueryParam.UuidParam.uuidParam;

@Repository
public class IngredientRepo {

    private final Repo repo;

    public IngredientRepo(Repo repo) {
        this.repo = repo;
    }

    public void insert(Ingredient ingredient) {
        repo.insert("instruments", new QueryParams()
                .add("id", uuidParam(ingredient.id))
                .add("name", textParam(ingredient.name))
                .add("createdAt", timestampParam(ingredient.createdAt))
                .add("updatedAt", timestampParam(ingredient.updatedAt)));
    }

    public List<Ingredient> selectAll() {
        return repo.select("select * from ingredients", QueryParams.EMPTY, IngredientRepo::extractIngredient);
    }

    public Ingredient selectById(UUID id) {
        return repo.select("select * from ingredients where id = ?",
                new QueryParams().add("id", uuidParam(id)),
                IngredientRepo::extractIngredient).getFirst();
    }

    private static Ingredient extractIngredient(ResultExtractor ex) {
        return new Ingredient(ex.uuid("id"),
                ex.text("name"),
                ex.instant("createdAt"),
                ex.instant("updatedAt"));
    }
}
