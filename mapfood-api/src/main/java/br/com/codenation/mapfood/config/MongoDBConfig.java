package br.com.codenation.mapfood.config;

import br.com.codenation.mapfood.repository.RestaurantsRepository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = RestaurantsRepository.class)
public class MongoDBConfig extends AbstractMongoConfiguration {

  @Override
  public MongoClient mongoClient() {
    String connection =
          "mongodb://mapfood:mapfood1104@ds137255.mlab.com:37255/mapfood";

    return new MongoClient(new MongoClientURI(connection));
  }

  @Override
  protected String getDatabaseName() {
    return "mapfood";
  }
}