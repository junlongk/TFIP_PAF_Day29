package sg.edu.nus.iss.day29order.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.day29order.models.Order;
import sg.edu.nus.iss.day29order.utils.DocumentConverter;

import static sg.edu.nus.iss.day29order.Constants.*;

@Repository
public class OrderRepository {

    @Autowired
    private MongoTemplate template;

    public void insertOrder (Order order) {
        // Convert order to document for processing
        Document document = DocumentConverter.orderToDocument(order);
        template.insert(document, COLLECTION_ORDERS);
    }
}
