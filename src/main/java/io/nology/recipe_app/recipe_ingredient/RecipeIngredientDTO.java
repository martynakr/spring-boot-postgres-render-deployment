package io.nology.recipe_app.recipe_ingredient;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
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
