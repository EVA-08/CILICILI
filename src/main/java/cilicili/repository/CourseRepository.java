package cilicili.repository;

import cilicili.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    public Course findByName(String name);

    public Set<Course> findByNameContaining(String queryString);
    public Set<Course> findAllBy();
}
