package ALP.KBEGateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ALP.RabbitMessage;
import ALP.KBEGateway.RabbitMQ.RabbitMQSender;

import static java.lang.Thread.sleep;

import java.io.Serializable;

@RestController
public class PriceController {

    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    /**
     * Post Mapping to calculate a price of a single product.
     * @param product the product one wants to get the price from.
     * @return the price of the product
     */
    @Deprecated
    @PostMapping("/price")
    public String calculatePrice(@RequestBody Serializable product) {
        returnMessage = null;
        System.out.println("sending message");
        RabbitMessage rabbitMessage = new RabbitMessage("getPrice", product);
        rabbitMessage.setAdditionalField("");
        rabbitMQSender.sendPrice(rabbitMessage);
        System.out.println("message sent");
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
     * Post mapping for uploading and saving CSV file into the DB
     * 
     * @param file the csv file to be uploaded
     * @return a success message
     */

    /*
     * @PostMapping("/uploadComponents")
     * public String uploadComponents(@RequestParam("file") MultipartFile file) {
     * 
     * componentService.readCSV(file);
     * return "Success: Components were updated";
     * }
     * 
     */
    public static void handle(String string) {
        returnMessage = string;
    }
}