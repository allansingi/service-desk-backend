package pt.allanborges.userserviceapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.allanborges.userserviceapi.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}