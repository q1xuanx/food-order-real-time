package ecomerece.food.order.services;

import ecomerece.food.order.dto.OrderRequest;
import ecomerece.food.order.dto.OrderResponse;
import ecomerece.food.order.enums.StatusEnum;
import ecomerece.food.order.models.Food;
import ecomerece.food.order.models.Order;
import ecomerece.food.order.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Transactional
    public Page<OrderResponse> getOrders(int page, int pageSize){
        Pageable pageRequest = PageRequest.of(page, pageSize, Sort.by("orderDate").ascending());
        Page<Order> orders = orderRepository.findAll(pageRequest);
        return orders.map(OrderResponse::toResponse);
    }

}
