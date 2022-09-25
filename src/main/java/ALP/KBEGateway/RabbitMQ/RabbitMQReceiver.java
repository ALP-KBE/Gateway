package ALP.KBEGateway.RabbitMQ;

import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ALP.RabbitMessage;
import ALP.KBEGateway.Controller.ComponentController;
import ALP.KBEGateway.Controller.PriceController;
import ALP.KBEGateway.Controller.ProductController;

@Component
@RabbitListener(queues = "main-queue", id = "listener")
public class RabbitMQReceiver {
    @Autowired
    ComponentController componentController;

    @Autowired
    ProductController productController;

    @Autowired
    PriceController priceController;

    @RabbitHandler
    public void receiver(RabbitMessage message) {
        System.out.println("message angekommen " + message.getValue());
        switch (message.getType()) {
            case "component":
                ComponentController.handle((String) message.getValue());
                break;
            case "product":
                if (message.getValue() instanceof LinkedHashMap) {
                    LinkedHashMap<String, String> lhm = (LinkedHashMap<String, String>) message.getValue();
                    lhm.forEach((key, value) -> System.out.println(key + " - " + value));
                } else {
                    ProductController.handle((String) message.getValue());
                }
                break;
            case "currency":
                ComponentController.handleCurrency((Double) message.getValue());
            //case "price":
            //    PriceController.handle((String) message.getValue());
            //    break;
        }
    }
}
