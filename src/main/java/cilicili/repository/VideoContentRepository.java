package cilicili.repository;

import cilicili.domain.Video;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource
public interface VideoContentRepository extends ContentStore<Video, String> {
}
