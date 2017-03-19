package com.corner.mogodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.acme.repositories.mongo")
@EnableJpaRepositories(basePackages = "com.acme.repositories.jpa")
class ZjjfMongoConfiguration extends AbstractMongoConfiguration {

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient();
  }

  @Override
  protected String getDatabaseName() {
    return "springdata";
  }
}