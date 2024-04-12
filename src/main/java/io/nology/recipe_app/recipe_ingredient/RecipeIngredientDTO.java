package io.nology.recipe_app.recipe_ingredient;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class RecipeIngredientDTO {
    
    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    @NotBlank
    private String quantity;

    @Getter
    @Setter
    @NotBlank
    private String unit;

}
