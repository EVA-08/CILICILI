package four.repository;

import four.domain.SubmittedHomework;
import org.springframework.data.repository.CrudRepository;

public interface SubmittedHomeworkRepository extends CrudRepository<SubmittedHomework, Integer> {
}
