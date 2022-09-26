package ALP.KBEGateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.RabbitMessage;
import ALP.KBEGateway.RabbitMQ.RabbitMQSender;

import static java.lang.Thread.sleep;

@RestController
public class ComponentController {

    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    /**
     * GetMapping for retrieving all available components in given currency.
     * @param currency the currency to convert. If not provided dollar will be used.
     * @return all available components in a json array
     */
    @GetMapping("/components")
    public String getComponents(@RequestParam(value = "currency", defaultValue = "") String currency) {
        returnMessage = null;
        rabbitMQSender.sendWarehouse(new RabbitMessage("getComponents", ""));
        while (returnMessage == null) {
            try {
                System.out.println("waiting");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String allComponents = returnMessage;
        returnMessage = null;
        RabbitMessage rabbitMessage = new RabbitMessage("components", allComponents);
        rabbitMessage.setAdditionalField(currency);
        if (!currency.equals("")) {
            rabbitMQSender.sendCurrency(rabbitMessage);
            while (returnMessage == null) {
                try {
                    System.out.println("waiting");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return returnMessage;
        } else {
            return allComponents;
        }
    }

    @GetMapping("/components/{id}")
    public ResponseEntity<String> getComponentWithId(@PathVariable("id") int objectId) {
        returnMessage = null;
        rabbitMQSender.sendWarehouse(new RabbitMessage("getComponents", String.valueOf(objectId)));
        while (returnMessage == null) {
            try {
                System.out.println("waiting");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (returnMessage.equals("IndexOutOfBoundsExceptionOops")) {
            return new ResponseEntity<>(
                    "component with specified id doesnt exist",
                    HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(
                    returnMessage,
                    HttpStatus.OK);
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