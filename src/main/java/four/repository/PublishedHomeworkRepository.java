package four.repository;

import four.domain.PublishedHomework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedHomeworkRepository extends CrudRepository<PublishedHomework, Integer> {
}
