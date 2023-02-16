package sg.edu.nus.iss.day29.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import static sg.edu.nus.iss.day29.Constants.*;

@Configuration
public class AppConfig {

    @Value("${mongo.url}")
    private String mongoUrl;

    @Bean
    public MongoTemplate createShows () {
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, SHOWS);
    }
}
