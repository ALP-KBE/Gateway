package ALP.KBEGateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ALP.RabbitMessage;
import ALP.KBEGateway.RabbitMQ.RabbitMQSender;

import static java.lang.Thread.sleep;


@RestController
public class ProductController {

    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    /**
     * Postmapping to add a single component to a product
     * 
     * @param component the component to add
     * @param name      the name of the product
     * @return the product as a json
     */
    @PostMapping("/component/{name}")
    public String postComponent(@RequestBody String component, @PathVariable String name) {
        returnMessage = null;
        RabbitMessage rabbitMessage = new RabbitMessage("postComponent", component);
        rabbitMessage.setAdditionalField(name);
        rabbitMQSender.sendProduct(rabbitMessage);
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String rMessageCopy = returnMessage;
        return rMessageCopy;
    }

    /**
     * Postmapping to add multiple components to a product
     * @param components the components to add
     * @param name the name of the product
     * @return the product as a json
     */
    @PostMapping("/components/{name}")
    public String postComponents(@RequestBody String components, @PathVariable String name) {
        returnMessage = null;
        RabbitMessage rabbitMessage = new RabbitMessage("postComponents", components);
        rabbitMessage.setAdditionalField(name);
        rabbitMQSender.sendProduct(rabbitMessage);
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String rMessageCopy = returnMessage;
        return rMessageCopy;
    }

    /**
     * Getmapping to fetch all products in selected currency
     * @param currency the currency the price of products should be
     * @return all products in selected currency as json list
     */
    @GetMapping("/products")
    public String getProducts(@RequestParam(value = "currency", defaultValue = "") String currency){
        returnMessage = null;
        RabbitMessage rabbitMessage = new RabbitMessage("getProducts", "");
        rabbitMQSender.sendProduct(rabbitMessage);
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String products = returnMessage;
        returnMessage = null;
        rabbitMessage = new RabbitMessage("getPrices", products);
        rabbitMQSender.sendPrice(rabbitMessage);
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products = returnMessage;
        returnMessage = null;
        rabbitMessage = new RabbitMessage("products", products);
        rabbitMessage.setAdditionalField(currency);
        rabbitMQSender.sendCurrency(rabbitMessage);
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products = returnMessage;
        return products;
    }
    public static void handle(String serializable) {
        returnMessage = serializable;
    }
}