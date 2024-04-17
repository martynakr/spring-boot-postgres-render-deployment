package io.nology.recipe_app;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import io.nology.recipe_app.ingredient.IngredientService;
import io.nology.recipe_app.recipe_ingredient.RecipeIngredient;
import io.nology.recipe_app.recipe_ingredient.RecipeIngredientDTO;
import io.nology.recipe_app.recipe_ingredient.RecipeIngredientRepository;
import io.nology.recipe_app.recipe_ingredient.RecipeIngredientService;

@ExtendWith(MockitoExtension.class)
public class RecipeIngredientServiceTest {

    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private RecipeIngredientService underTest;

    @Test
    void createRecipeIngredient_ValidData_AddsToDB() {

        RecipeIngredientDTO data = new RecipeIngredientDTO("Parmesan", "200", "g");

        BDDMockito.given(ingredientService.findByName("parmesan")).willReturn(Optional.empty());

        this.underTest.create(data);
        ArgumentCaptor<RecipeIngredient> recipeIngredientArgument = ArgumentCaptor.forClass(RecipeIngredient.class);

        Mockito.verify(recipeIngredientRepository).save(recipeIngredientArgument.capture());

    }
    
}
