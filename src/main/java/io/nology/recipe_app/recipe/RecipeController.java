package io.nology.recipe_app.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
       return new ResponseEntity<>(this.recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Long id) {
        Optional<Recipe> maybeRecipe = this.recipeService.findById(id);

        if(maybeRecipe.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeRecipe.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> create(@Valid @RequestBody RecipeDTO data) {
        Recipe created = this.recipeService.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
}
