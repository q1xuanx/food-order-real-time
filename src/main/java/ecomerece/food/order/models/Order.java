package ecomerece.food.order.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int customerId;
    @OneToMany(mappedBy = "order", cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Food> foods = new ArrayList<>();
    private Date orderDate;
}
