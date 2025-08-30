package ecomerece.food.order.dto;

import ecomerece.food.order.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponse {
    private int orderId;
    private int customerId;
    private List<FoodResponse> foods;
    private Date orderDate;

    public static OrderResponse toResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.orderId = order.getOrderId();
        orderResponse.customerId = order.getCustomerId();
        orderResponse.orderDate = order.getOrderDate();
        orderResponse.foods = order
                .getFoods()
                .stream()
                .map(food -> {
                    FoodResponse foodResponse = new FoodResponse();
                    foodResponse.setFoodName(food.getFoodName());
                    foodResponse.setFoodDescription(food.getFoodDescription());
                    foodResponse.setPrice(food.getPrice());
                    return foodResponse;
                }).collect(Collectors.toList());
        return orderResponse;
    }
}

