package ecomerece.food.order.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
public class HealthCheckController {
    // Api health check
    @GetMapping("/health-check")
    public Map<String, Object> healthCheck() {
        log.info("Health check started");
        Map<String, Object> response = Map.of("message", "SUCCESS", "status", true);
        log.info("Health check finished with data: {} and time: {}", response, Date.from(Instant.now()));
        return response;
    }
}
