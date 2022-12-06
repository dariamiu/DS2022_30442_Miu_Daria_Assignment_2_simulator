package ds.smartmeteringdevicesimulator;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
public class MessageController {
    @Autowired
    private RabbitTemplate template;

    public void publishMessage(CustomMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setTimestamp(new Date());
        System.out.println("alooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, message);
        System.out.println("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
