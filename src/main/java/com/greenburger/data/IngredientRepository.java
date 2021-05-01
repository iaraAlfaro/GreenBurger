package com.greenburger.data;

import org.springframework.data.repository.CrudRepository;
import com.greenburger.Ingredient;


public interface IngredientRepository
        extends CrudRepository<Ingredient, String>{
}
