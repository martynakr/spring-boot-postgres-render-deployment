package io.nology.recipe_app.recipe;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nology.recipe_app.recipe_ingredient.RecipeIngredient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String title;

    @Getter
    @Setter
    @Column
    private String instructions;

    @Getter
    @Setter
    @Column
    private Integer serves;

    @Getter
    @Setter
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();

    public Recipe(String title, String instructions, Integer serves) {
        this.title = title;
        this.instructions = instructions;
        this.serves = serves;
    }

}
