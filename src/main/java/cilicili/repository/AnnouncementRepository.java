package cilicili.repository;

import cilicili.domain.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementRepository extends CrudRepository<Announcement, Integer> {
}
