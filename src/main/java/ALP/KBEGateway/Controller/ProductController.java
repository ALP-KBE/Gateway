package ALP.KBEGateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.RabbitMessage;
import ALP.KBEGateway.Model.Component;
import ALP.KBEGateway.Model.Product;
import ALP.KBEGateway.RabbitMQ.RabbitMQSender;

import static java.lang.Thread.sleep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ProductController {

    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    // TODO: get all available Products

    /**
     * Postmapping to add a single component to a product
     * 
     * @param component the component to add
     * @param name      the name of the product
     * @return the product as a string
     */
    @PostMapping("/component/{name}")
    public String postComponent(@RequestBody Component component, @PathVariable String name) {
        returnMessage = null;
        RabbitMessage rabbitMessage = new RabbitMessage("postComponent", component);
        rabbitMessage.setAdditionalField(name);
        rabbitMQSender.sendProduct(rabbitMessage);
        // while (returnMessage == null) {
        // try {
        // System.out.println("und wir warten");
        // sleep(10);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        String rMessageCopy = returnMessage;
        return rMessageCopy;
    }

    @PostMapping("/components/{name}")
    public String postComponents(@RequestBody LinkedList<Component> components, @PathVariable String name) {
        returnMessage = null;
        RabbitMessage rabbitMessage = new RabbitMessage("postComponents", components);
        rabbitMessage.setAdditionalField(name);
        rabbitMQSender.sendProduct(rabbitMessage);
        // while (returnMessage == null) {
        // try {
        // System.out.println("und wir warten");
        // sleep(10);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        String rMessageCopy = returnMessage;
        return rMessageCopy;
    }

    public static void handle(Serializable serializable) {
        if (serializable instanceof LinkedHashMap) {
            LinkedHashMap<?, ?> lhm = (LinkedHashMap<?, ?>) serializable;
            lhm.forEach((key, value) -> {
                if (value instanceof String) {
                    System.out.println(key + " - " + value);
                } else if (value instanceof ArrayList) {
                    System.out.print(key);
                    for (Object obj : (ArrayList) value) {
                        if (obj instanceof String) {
                            System.out.print(" - " + obj);
                        } else if (obj instanceof LinkedHashMap) {
                            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) obj;
                            linkedHashMap.forEach((k, v) -> {
                                System.out.println(" + " + k + " - " + v);
                            });
                        }
                    }
                }
            });
        }
    }
}