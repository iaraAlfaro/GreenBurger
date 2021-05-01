package com.greenburger.web;

import com.greenburger.data.IngredientRepository;
import com.greenburger.Burger;
import com.greenburger.Ingredient;
import com.greenburger.Ingredient.Type;
import com.greenburger.Order;
import com.greenburger.User;
import com.greenburger.data.BurgerRepository;
import com.greenburger.data.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignBurgerController {

    private final IngredientRepository ingredientRepo;

    private BurgerRepository burgerRepo;

    private UserRepository userRepo;

    @Autowired
    public DesignBurgerController(
            IngredientRepository ingredientRepo,
            BurgerRepository burgerRepo,
            UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.burgerRepo = burgerRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "burger")
    public Burger burger(){
        return new Burger();
    }
    
    @ModelAttribute(name = "user")
    public User user(Principal principal){
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        return user;
    }
    
    @GetMapping
    public String showDesignForm(Model model) {
        return "design";
    }

    @PostMapping
    public String processBurger(
            @Valid Burger burger, Errors errors,
            @ModelAttribute Order order) {
        
        if (errors.hasErrors()) {
            return "design";
        }
        
        Burger saved = burgerRepo.save(burger);
        order.addBurger(saved);
        
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
