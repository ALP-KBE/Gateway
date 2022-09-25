package ALP.KBEGateway.RabbitMQ;

import ALP.RabbitMessage;
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
    @Autowired
    private Queue productQueue;
    @Autowired
    private Queue currencyQueue;
    public void sendWarehouse(Serializable serializable) {
        rabbitTemplate.convertAndSend(warehouseQueue.getName(), serializable);
    }

    public void sendPrice(Serializable serializable) {
        rabbitTemplate.convertAndSend(priceQueue.getName(), serializable);
    }

    public void sendProduct(Serializable serializable) {
        rabbitTemplate.convertAndSend(productQueue.getName(), serializable);
    }

    public void sendCurrency(Serializable serializable) {
        rabbitTemplate.convertAndSend(currencyQueue.getName(), serializable);
    }
}

