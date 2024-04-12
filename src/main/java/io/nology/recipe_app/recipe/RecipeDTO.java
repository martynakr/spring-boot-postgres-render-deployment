package io.nology.recipe_app.recipe;

import io.nology.recipe_app.recipe_ingredient.RecipeIngredientDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class RecipeDTO {

    @NotBlank
    @Getter
    @Setter
    private String title;

    @NotBlank
    @Getter
    @Setter
    private String instructions;

    @Getter
    @Setter
    @Min(1)
    private Integer serves;

    
    @Getter
    @Setter
    @NotEmpty
    @Valid
    private RecipeIngredientDTO[] ingredients;

}
