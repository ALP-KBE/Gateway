package ALP.KBEGateway.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ALP.KBEGateway.Controller.ComponentController;
import ALP.KBEGateway.Controller.PriceController;

@Component
@RabbitListener(queues = "main-queue", id = "listener")
public class RabbitMQReceiver {
    @Autowired
    ComponentController componentController;

    @Autowired
    PriceController priceController;

    @RabbitHandler
    public void receiver(String string) {
        System.out.println("message angekommen " + string);
        ComponentController.handle(string);
    }
}
