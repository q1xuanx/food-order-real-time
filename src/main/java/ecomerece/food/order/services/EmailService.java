package ecomerece.food.order.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public boolean sendOrderConfirmationEmail(String orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        log.info("Start sending email for order {}", orderId);
        // Set send and receive
        message.setFrom("nhoang2929@gmail.com");
        message.setTo("nhanphmhoang@gmail.com");
        // Set subject and message for user
        message.setSubject("Your Order Confirmation");
        message.setText("Your order have been confirmed, check your order id: [" + orderId + "]");
        // Start send email
        try {
            mailSender.send(message);
        } catch (MailException mailFail) {
            log.error(mailFail.getMessage());
            return false;
        }
        log.info("End sending email for order {}", orderId);
        return true;
    }
}
