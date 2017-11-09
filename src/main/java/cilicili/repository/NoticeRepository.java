package cilicili.repository;

import cilicili.domain.Notice;
import org.springframework.data.repository.CrudRepository;

public interface NoticeRepository extends CrudRepository<Notice, Integer> {
}
