package cilicili.service;

import cilicili.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResourceService {
    private Path location = Paths.get("target/classes/static/resources");
    private ResourceRepository resourceRepository;

    public ResourceService() {
    }

    public void setLocation(String location) {
        this.location = Paths.get(location);
    }

    @Autowired
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource load(String filename) {
        try {
            Path file = location.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename);
        }
    }

    public String getResourcePath(Integer resourceId) {
        return resourceRepository.findOne(resourceId).getPath();
    }
}
