package ecomerece.food.order.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFood;
    private String foodName;
    private String foodDescription;
    private String price;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
}
