package ALP.KBEGateway.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

import java.io.Serializable;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Queue warehouseQueue;
    @Autowired
    private Queue priceQueue;

    public void sendWarehouse(Serializable serializable) {
        rabbitTemplate.convertAndSend(warehouseQueue.getName(), serializable);
    }

    public void sendPrice(Serializable serializable) {
        rabbitTemplate.convertAndSend(priceQueue.getName(), serializable);
    }
}
