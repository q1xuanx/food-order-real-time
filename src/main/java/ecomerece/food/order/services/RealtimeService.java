package ecomerece.food.order.services;

import ecomerece.food.order.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class RealtimeService {

    private final SimpMessagingTemplate messagingTemplate;

    public String newOrderAppear(String msgOrder) {
        String statusForUpdate = msgOrder.isBlank() ? StatusEnum.ERROR.name() : StatusEnum.OK.name();

        try {
            messagingTemplate.convertAndSend("/topic/order", msgOrder);
        }catch (MessagingException me) {
            log.error("Error while sending into websocket topic {} details {}", "/topic/order" ,me.getMessage());
            return StatusEnum.ERROR_WHEN_SEND_MESSAGE_WS.name();
        }
        log.info("Send message to websocket topic {} success", "topic/order");
        return statusForUpdate;
    }
}
