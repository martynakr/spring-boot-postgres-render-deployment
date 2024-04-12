package io.nology.recipe_app.recipe_ingredient;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.recipe_app.ingredient.Ingredient;
import io.nology.recipe_app.ingredient.IngredientService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecipeIngredientService {
    
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private IngredientService ingredientService;

    public Optional<RecipeIngredient> findById(Long id) {
        return this.recipeIngredientRepository.findById(null);
    }
    public RecipeIngredient create(RecipeIngredientDTO data) {

        RecipeIngredient newRecipeIngredient = new RecipeIngredient();
        newRecipeIngredient.setQuantity(data.getQuantity());
        newRecipeIngredient.setUnit(data.getUnit());

        Optional<Ingredient> foundIngredient = this.ingredientService.findByName(data.getName().toLowerCase());

        if(foundIngredient.isEmpty()) {
            Ingredient created = this.ingredientService.create(data.getName().toLowerCase());
            newRecipeIngredient.setIngredient(created);
            
        } else {
            newRecipeIngredient.setIngredient(foundIngredient.get());
        }

        return this.recipeIngredientRepository.save(newRecipeIngredient);
    }
}
