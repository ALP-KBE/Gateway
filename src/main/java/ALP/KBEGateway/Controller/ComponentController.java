package ALP.KBEGateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.KBEGateway.RabbitMQ.RabbitMQSender;
import ALP.KBEGateway.RabbitMQ.RabbitMessage;

import static java.lang.Thread.sleep;

@RestController
public class ComponentController {

    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    /**
     * Get mapping that returns all components of given type. If no type was
     * specified, all components are returned.
     * 
     * @param id the type of the component
     * @return all components (of given type)
     */
    @GetMapping("/components")
    public String getComponents(@RequestParam(value = "id", defaultValue = "") String id) {
        returnMessage = null;
        System.out.println("sending message");
        rabbitMQSender.sendWarehouse(new RabbitMessage("getComponents", ""));
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

    @GetMapping("/components/{id}")
    public ResponseEntity<String> getComponentWithId(@PathVariable("id") int objectId) {
        returnMessage = null;
        rabbitMQSender.sendWarehouse(new RabbitMessage("getComponents", String.valueOf(objectId)));
        while (returnMessage == null) {
            try {
                System.out.println("und wir warten");
                sleep(10);
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