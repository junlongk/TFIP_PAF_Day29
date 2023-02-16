package sg.edu.nus.iss.day29;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sg.edu.nus.iss.day29.services.TvService;

import java.util.List;

@SpringBootApplication
public class Day29Application implements CommandLineRunner {

    @Autowired
    private TvService tvSvc;

    public static void main(String[] args) {
        SpringApplication.run(Day29Application.class, args);
    }

    @Override
    public void run(String... args) {
        // List<Document> results = tvSvc.countGenres();
        List<Document> results = tvSvc.histogramOfRatings();

        for (Document d: results) {
            System.out.printf(">>> %s\n", d.toJson());
        }
    }
}
