package com.example.demo.service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.lenskit.LenskitConfiguration;
import org.lenskit.api.ItemRecommender;
import org.lenskit.api.Recommender;
import org.lenskit.api.Result;
import org.lenskit.api.ResultList;
import org.lenskit.baseline.MeanDamping;
import org.lenskit.baseline.UserMeanItemScorer;
import org.lenskit.basic.TopNItemRecommender;
import org.lenskit.config.ConfigHelpers;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.CSVDataSource;
import org.lenskit.knn.user.NeighborhoodSize;
import org.lenskit.knn.user.UserUserNeighborhood;
import org.lenskit.knn.user.UserUserSimilarity;
import org.lenskit.knn.user.UserVectorSimilarity;
import org.springframework.core.io.ClassPathResource;

public class LenskitRecommender {
    public static List<Long> getRecommendations(long userId, int numRecommendations) {
        try {
            // Load CSV data from the classpath (Spring Boot resources)
            File file = new ClassPathResource("templates/ratings.csv").getFile();
            DataAccessObject dao = CSVDataSource.open(file).get();

            // Create LensKit configuration
            LenskitConfiguration config = new LenskitConfiguration();
            config.bind(ItemRecommender.class).to(TopNItemRecommender.class);
            config.bind(UserVectorSimilarity.class).to(UserUserSimilarity.class);
            config.bind(UserUserNeighborhood.class).to(NeighborhoodSize.class).set(30);
            config.bind(UserMeanItemScorer.class).to(MeanDamping.class).set(5.0);

            // Train and build the recommendation engine
            Recommender rec = ConfigHelpers.build(config, dao);

            // Generate recommendations
            ItemRecommender itemRec = rec.getItemRecommender();
            ResultList results = itemRec.recommendWithDetails(userId, numRecommendations);

            return results.stream().map(Result::getId).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
