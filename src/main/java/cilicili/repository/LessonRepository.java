package cilicili.repository;

import cilicili.domain.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
    Lesson findByName(String name);

    Lesson findByCourseIdAndSequence(Integer courseId, Integer sequence);
}
