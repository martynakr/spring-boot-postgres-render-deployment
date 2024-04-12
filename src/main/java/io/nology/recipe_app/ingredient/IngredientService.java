package io.nology.recipe_app.ingredient;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient create(String name){
        return this.ingredientRepository.save(new Ingredient(name));
    }

    public Optional<Ingredient> findByName(String name) {
        return this.ingredientRepository.findByName(name);
    }
    
}
