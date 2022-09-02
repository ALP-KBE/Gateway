package ALP.KBEWarehouse;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "main-queue", id="listener")
public class RabbitMQReceiver {
    @Autowired
    ComponentController componentController;
    @RabbitHandler
    public void receiver(String string) {
        System.out.println("message angekommen "+string);
        ComponentController.handle(string);
    }
}
