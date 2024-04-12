package io.nology.recipe_app.recipe;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.recipe_app.recipe_ingredient.RecipeIngredient;
import io.nology.recipe_app.recipe_ingredient.RecipeIngredientDTO;
import io.nology.recipe_app.recipe_ingredient.RecipeIngredientService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecipeService {
    
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientService recipeIngredientService;

    public List<Recipe> findAll() {
        return this.recipeRepository.findAll();
    }

    public Optional<Recipe> findById(Long id) {
        return this.recipeRepository.findById(id);
    }


    public Recipe create(RecipeDTO data) {
        Recipe newRecipe = new Recipe(data.getTitle().toLowerCase(), data.getInstructions(), data.getServes());

        RecipeIngredientDTO[] recipeIngredients = data.getIngredients();

        Recipe created = this.recipeRepository.save(newRecipe);

        for(int i = 0; i < recipeIngredients.length; i++) {
            RecipeIngredient newRecipeIngredient = this.recipeIngredientService.create(recipeIngredients[i]);
            newRecipeIngredient.setRecipe(created);
            newRecipe.getRecipeIngredients().add(newRecipeIngredient);
        }
         
        return created;
    }
}
