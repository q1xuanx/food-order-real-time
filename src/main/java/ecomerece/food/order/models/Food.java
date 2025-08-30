package ecomerece.food.order.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_order_id")
    @JsonBackReference
    private Order order;
}
