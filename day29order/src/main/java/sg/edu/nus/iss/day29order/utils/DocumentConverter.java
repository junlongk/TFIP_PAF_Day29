package sg.edu.nus.iss.day29order.utils;

import org.bson.Document;
import sg.edu.nus.iss.day29order.models.LineItem;
import sg.edu.nus.iss.day29order.models.Order;

public class DocumentConverter {

    public static Document orderToDocument(Order order) {
        Document document = new Document();
        document.put("orderId", order.getOrderId());
        document.put("name", order.getName());
        document.put("email", order.getEmail());
        document.put("deliveryDate", order.getDeliveryDate());
        document.put("lineItems", order.getLineItems().stream()
                .map(v -> lineItemToDocument(v))
                .toList());
        return document;
    }

    public static Document lineItemToDocument(LineItem lineItem) {
        Document document = new Document();
        document.put("item", lineItem.getItem());
        document.put("quantity", lineItem.getQuantity());
        return document;
    }
}
