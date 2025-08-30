package ecomerece.food.order.dto;

import ecomerece.food.order.models.Food;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private int customerId;
    private List<Food> foods = new ArrayList<>();
}
