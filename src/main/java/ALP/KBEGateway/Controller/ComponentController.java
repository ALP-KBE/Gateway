package ALP.KBEGateway.Controller;

import ALP.KBEGateway.Model.Currency;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.KBEGateway.RabbitMQ.RabbitMQSender;
import ALP.RabbitMessage;

import static java.lang.Thread.sleep;

@RestController
public class ComponentController {

    private static String returnMessage;
    private static float adjustedPrice;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private Gson gson;


    /**
     * Get mapping that returns all components of given type. If no type was
     * specified, all components are returned.
     * 
     * @param id the type of the component
     * @return all components (of given type)
     */
    @GetMapping("/components")
    public String getComponents(@RequestParam(defaultValue = "") String id, @RequestParam(defaultValue="DOLLAR") Currency currency) {
        returnMessage = null;
        adjustedPrice =0.0F;
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
        JsonObject component=gson.fromJson(returnMessage, JsonObject.class);
        RabbitMessage rabbitMessage=new RabbitMessage("getAdjustedPrice", component.get("preis").getAsFloat());
        rabbitMessage.setAdditionalField(currency);
        rabbitMQSender.sendCurrency(rabbitMessage);
        while(adjustedPrice==0.0)   {
            try {
                System.out.println("warten auf CurrencyService");
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        component.addProperty("adjustedPrice", adjustedPrice);
        return component.toString();
    }

    @GetMapping("/components/{id}")
    public ResponseEntity<String> getComponentWithId(@PathVariable("id") int objectId, @RequestParam(defaultValue="RIEL") Currency currency) {
        returnMessage = null;
        adjustedPrice=0.0F;
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
        } else  {
            JsonObject component=gson.fromJson(returnMessage, JsonObject.class);
            RabbitMessage rabbitMessage=new RabbitMessage("getAdjustedPrice", component.get("preis").getAsFloat());
            rabbitMessage.setAdditionalField(currency);
            rabbitMQSender.sendCurrency(rabbitMessage);
            while(adjustedPrice==0.0)   {
                try {
                    System.out.println("warten auf CurrencyService");
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            component.addProperty("adjustedPrice", adjustedPrice);
            return new ResponseEntity<>(
                    component.toString(),
                    HttpStatus.OK);
        }
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

    public static void handleCurrency(Double value) {
        adjustedPrice=value.floatValue();
    }
}