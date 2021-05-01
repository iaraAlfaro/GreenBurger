package com.greenburger;

import com.greenburger.Ingredient.Type;
import com.greenburger.data.IngredientRepository;
import com.greenburger.data.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    public CommandLineRunner dataLoader(IngredientRepository repo,
            UserRepository userRepo, PasswordEncoder encoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.deleteAll();
                userRepo.deleteAll();

                repo.save(new Ingredient("WHBR", "Wheat Bread", Type.BREAD));
                repo.save(new Ingredient("WHGR", "Whole Grain Bread", Type.BREAD));
                repo.save(new Ingredient("LERO", "Lentils and Rosemary", Type.BURGER));
                repo.save(new Ingredient("BEPR", "Beans and Provençal", Type.BURGER));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGETABLES));
                repo.save(new Ingredient("LETC", "Lettuce", Type.VEGETABLES));
                repo.save(new Ingredient("CHED", "Vegan Cheddar", Type.CHEESE));
                repo.save(new Ingredient("JACK", "Vegan Monterrey Jack", Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Vegan Salsa", Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Vegan Sour Cream", Type.SAUCE));

                userRepo.save(new User("habuma", encoder.encode("password"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234"));

            }
        };
    }
}
