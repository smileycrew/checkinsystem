package com.github.smileycrew.CheckInSystem.checkinsystem;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends MongoRepository<Guest, ObjectId> {}
