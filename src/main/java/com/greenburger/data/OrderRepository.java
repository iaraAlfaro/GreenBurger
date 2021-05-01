package com.greenburger.data;

import com.greenburger.Order;
import com.greenburger.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Iara
 */
public interface OrderRepository
        extends CrudRepository<Order, Long> {

    // tag::findByUser_paged[]
    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
    // end::findByUser_paged[]

    /*
    // tag::findByUser[]
    List<Order> findByUserOrderByPlacedAtDesc(User user);
    // end::findByUser[]
     */
}
