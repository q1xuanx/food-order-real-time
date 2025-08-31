package ecomerece.food.order.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/ws/topic");
        registry.setApplicationDestinationPrefixes("/order");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This is endpoint is for client can connect to this websocket
        registry.addEndpoint("ws/order");
    }
}
