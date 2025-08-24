package ecomerece.food.order.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RestController("/api/")
@Slf4j
public class OrderController {
    @PostMapping("order")
    public String createOrder() {
        // Todo: update this function
        log.info("Start create order at {}", Date.from(Instant.now()));

        return "Order created";
    }
}
