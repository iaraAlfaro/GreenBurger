package com.greenburger.data;

import com.greenburger.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long>{
    
    User findByUsername(String username);
    
}
