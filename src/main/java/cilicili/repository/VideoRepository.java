package cilicili.repository;

import cilicili.domain.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "video", collectionResourceRel = "video")
public interface VideoRepository extends CrudRepository<Video, Integer>{
}
