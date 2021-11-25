package com.example.dbspringapp.repository;

import com.example.dbspringapp.model.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public class CellRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public CellRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Optional<Cell> findCellGivenCoord(double latitude, double longitude) {

        Query query = new Query(Criteria.where("latitude.0").lte(latitude).and("latitude.1").gt(latitude)
                .and("longitude.0").lte(longitude).and("longitude.1").gt(longitude));

        return Optional.ofNullable(mongoTemplate.findOne(query, Cell.class));
    }

    public Cell saveCell(Cell cell) {
        return mongoTemplate.save(cell);
    }
}
