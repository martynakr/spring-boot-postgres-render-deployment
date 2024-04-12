package io.nology.recipe_app.recipe_ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nology.recipe_app.ingredient.Ingredient;
import io.nology.recipe_app.recipe.Recipe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipe_ingredients")
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredient {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Getter
    @Setter
    @Column
    private String quantity;

    @Getter
    @Setter
    @Column
    private String unit;

    @Getter
    @Setter
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
