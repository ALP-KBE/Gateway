package ALP.KBEGateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.KBEGateway.Model.Product;
import ALP.KBEGateway.RabbitMQ.RabbitMQSender;
import ALP.KBEGateway.RabbitMQ.RabbitMessage;

import static java.lang.Thread.sleep;

@RestController
public class PriceController {

    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;


    @PostMapping("/price")
    public String calculatePrice(@RequestBody Product product) {
        returnMessage = null;
        System.out.println("sending message");
        rabbitMQSender.sendPrice(new RabbitMessage("getPrice", product));
        System.out.println("message sent");
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(10);
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