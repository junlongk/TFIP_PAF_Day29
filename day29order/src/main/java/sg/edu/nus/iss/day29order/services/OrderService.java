package sg.edu.nus.iss.day29order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.iss.day29order.models.Order;
import sg.edu.nus.iss.day29order.repositories.OrderRepository;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public String addOrder(Order order) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order.setOrderId(orderId);
        orderRepo.insertOrder(order);
        return orderId;
    }
}
