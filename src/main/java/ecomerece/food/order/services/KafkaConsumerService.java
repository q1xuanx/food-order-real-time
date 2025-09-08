package ecomerece.food.order.services;

import ecomerece.food.order.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final EmailService emailService;
    private final RealtimeService realtimeService;

    @KafkaListener(topics = "TopicOrder", groupId = "group-kitchen")
    public void processToKitchen(String message) {
        String realTimeStatus = realtimeService.newOrderAppear(message);
        if (realTimeStatus.equals(StatusEnum.ERROR_WHEN_SEND_MESSAGE_WS.name())) {
            log.error("Error when handle real time message {}", realTimeStatus);
        }
        log.info("Handle real time message {}", realTimeStatus);
    }

    @KafkaListener(topics = "TopicOrder", groupId = "group-email")
    public void processToEmail(String message) {
        boolean isSent = emailService.sendOrderConfirmationEmail(message);
        if (!isSent) {
            log.error("Error when sent email to order {}", message);
        }
        log.info("Email sent successfully");
    }

}
