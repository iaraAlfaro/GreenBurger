package com.greenburger.data;

import com.greenburger.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository 
        extends CrudRepository<Burger, Long>{
    
}
