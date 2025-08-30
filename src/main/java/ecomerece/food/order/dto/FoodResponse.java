package ecomerece.food.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodResponse {
    private String foodName;
    private String foodDescription;
    private String price;
}
