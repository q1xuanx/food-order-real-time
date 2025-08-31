package ecomerece.food.order.controllers;


import ecomerece.food.order.dto.OrderRequest;
import ecomerece.food.order.dto.OrderResponse;
import ecomerece.food.order.enums.StatusEnum;
import ecomerece.food.order.models.Order;
import ecomerece.food.order.services.OrderService;
import ecomerece.food.order.services.RealtimeService;
import ecomerece.food.order.ultility.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final RealtimeService realtimeService;
    // Guest order
    private final int DEFAULT_CUSTOMER_ID = 777;

    @PostMapping("/order")
    public ResponseEntity<Map<String,Object>> createOrder(@RequestBody OrderRequest order) throws Exception {
        // Todo: update this function
        try {
            Date current = Date.from(Instant.now());
            log.info("Start making order at {}", current);

            // Set default customer id
            if (order.getCustomerId() == 0) {
                order.setCustomerId(DEFAULT_CUSTOMER_ID);
            }

            String validateStatus = orderService.validateOrder(order.getFoods());
            if (!StatusEnum.OK.name().equals(validateStatus)) {
                Map<String, String> emptyOrder = Map.of("status", validateStatus);
                return ResponseEntity.ok(BaseResponse.makeResponse(emptyOrder, StatusEnum.FAILURE.name()));
            }

            Order makeOrder = Order.builder()
                    .foods(order.getFoods())
                    .orderDate(current)
                    .customerId(order.getCustomerId())
                    .build();

            boolean isCreated = orderService.makeOrder(makeOrder);

            if (isCreated) {
                log.info("Order maked successfully");
                Map<String, Object> orderCreated = Map.of("message", "Order created", "orderAt", makeOrder.getOrderDate());
                return ResponseEntity.ok(BaseResponse.makeResponse(orderCreated, StatusEnum.SUCCESS.name()));
            }

            log.info("Order making failed");
            Map<String, Object> failedOrder = Map.of("message", "Order failed to be created");
            return ResponseEntity.ok(BaseResponse.makeResponse(failedOrder, StatusEnum.FAILURE.name()));

        }catch (Exception e){
            log.error(e.getMessage());
            Map<String, Object> exceptionResponse = BaseResponse.makeResponse(Map.of("message", e.getMessage()), StatusEnum.ERROR.name());
            return ResponseEntity.ok(exceptionResponse);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<Map<String, Object>> getOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int pageSize) throws Exception {
        Page<OrderResponse> orders = orderService.getOrders(page, pageSize);
        Map<String, Object> response = Map.of(
                "items", orders.getContent(),
                "totalsPage", orders.getTotalPages(),
                "totalItems", orders.getTotalElements(),
                "currentPage", orders.getNumber()
        );
        return ResponseEntity.ok(BaseResponse.makeResponse(response, StatusEnum.SUCCESS.name()));
    }
}
