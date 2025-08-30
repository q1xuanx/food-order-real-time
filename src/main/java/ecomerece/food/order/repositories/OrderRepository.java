package ecomerece.food.order.repositories;

import ecomerece.food.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
