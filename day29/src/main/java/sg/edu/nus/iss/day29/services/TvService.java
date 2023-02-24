package sg.edu.nus.iss.day29.services;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static sg.edu.nus.iss.day29.Constants.*;

@Service
public class TvService {

    @Autowired
    private MongoTemplate template;

    /*
    db.tv.aggregate([
        { $unwind: "$genres" },
        {
            $group: {
                _id: "$genres",
                total: { $sum: 1}
            }
        },
        {
            $sort: { _id: 1}
        }
    ])
     */

    public List<Document> countGenres() {
        // $unwind
        UnwindOperation unwindGenres = Aggregation.unwind(FIELD_GENRES);

        // $group
        GroupOperation groupShowsByGenres = Aggregation.group(FIELD_GENRES)
                .count().as(FIELD_TOTAL);

        // $sort
        SortOperation sortByGenres = Aggregation.sort(
                Sort.by(Sort.Direction.ASC, FIELD_UNDERSCORE_ID));

        Aggregation pipeline = Aggregation.newAggregation(
                unwindGenres, groupShowsByGenres, sortByGenres);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }

    /*
    db.tv.aggregate([
        {
            $bucket: {
                groupBy: "$rating.average",
                boundaries: [3, 6, 9],
                default: ">9",
                output: {
                    titles: { $push: "$name" }
                }
            }
        }
    ])
     */

    public List<Document> histogramOfRatings() {

        BucketOperation ratingsBucket = Aggregation.bucket(FIELD_RATING_AVERAGE)
                .withBoundaries(3, 6, 9)
                .withDefaultBucket(">9")
                .andOutput(FIELD_NAME).push().as(FIELD_TITLES);

        Aggregation pipeline = Aggregation.newAggregation(ratingsBucket);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }
}
