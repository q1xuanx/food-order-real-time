package ecomerece.food.order.services;

import ecomerece.food.order.enums.StatusEnum;
import ecomerece.food.order.models.Food;
import ecomerece.food.order.models.Order;
import ecomerece.food.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public String validateOrder(List<Food> foods){
        if (foods.isEmpty()){
            return "No food to order, please try again";
        }
        return StatusEnum.OK.name();
    }

    public boolean makeOrder(Order order){
        try {
            // Add order id for each food ordered
            order.getFoods().forEach(food -> food.setOrder(order));
            orderRepository.save(order);
            orderRepository.flush();
        }catch (Exception e){
            log.error("Error saving order: {}", e.getMessage());
            return false;
        }
        return true;
    }

}
