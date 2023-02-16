package sg.edu.nus.iss.day29order.controllers;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.iss.day29order.models.Order;
import sg.edu.nus.iss.day29order.services.OrderService;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody Order order) {
        System.out.printf("%s\n", order);

        String orderId = orderSvc.addOrder(order);
        System.out.printf(">>> orderId: %s\n", orderId);

        JsonObject resp = Json.createObjectBuilder()
                .add("orderId", orderId)
                .add("message", "Order created successfully!")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resp.toString());
    }
}
