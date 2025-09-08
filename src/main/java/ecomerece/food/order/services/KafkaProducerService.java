package ecomerece.food.order.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        CompletableFuture<SendResult<String, String>> sendStatus = kafkaTemplate.send(topic, message);
        sendStatus.thenAccept(result -> {
            log.info("Message sent to topic {} with offset {}", topic, result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error(ex.getMessage());
            return null;
        });
    }
}
